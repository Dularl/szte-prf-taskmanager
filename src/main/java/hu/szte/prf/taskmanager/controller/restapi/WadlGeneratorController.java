package hu.szte.prf.taskmanager.controller.restapi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.jvnet.ws.wadl.Application;
import org.jvnet.ws.wadl.Doc;
import org.jvnet.ws.wadl.Param;
import org.jvnet.ws.wadl.ParamStyle;
import org.jvnet.ws.wadl.Representation;
import org.jvnet.ws.wadl.Request;
import org.jvnet.ws.wadl.Resource;
import org.jvnet.ws.wadl.Resources;
import org.jvnet.ws.wadl.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/rest/application.wadl")
public class WadlGeneratorController {
	String xs_namespace = "http://www.w3.org/2001/XMLSchema";
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/xml" })
	public @ResponseBody Application generateWadl(final HttpServletRequest request) {
		final Application result = new Application();
		final Doc doc = new Doc();
		doc.setTitle("Spring REST Service WADL");
		result.getDoc().add(doc);
		final Resources wadResources = new Resources();
		wadResources.setBase(getBaseUrl(request));

		final Map<RequestMappingInfo, HandlerMethod> handletMethods = handlerMapping.getHandlerMethods();
		for (final Map.Entry<RequestMappingInfo, HandlerMethod> entry : handletMethods.entrySet()) {

			final HandlerMethod handlerMethod = entry.getValue();

			final Object object = handlerMethod.getBean();
			final Object bean = webApplicationContext.getBean(object.toString());

			final boolean isRestContoller = bean.getClass().isAnnotationPresent(RestController.class);
			if (!isRestContoller) {
				continue;
			}
			final RequestMappingInfo mappingInfo = entry.getKey();

			final Set<String> pattern = mappingInfo.getPatternsCondition().getPatterns();
			final Set<RequestMethod> httpMethods = mappingInfo.getMethodsCondition().getMethods();
			final ProducesRequestCondition producesRequestCondition = mappingInfo.getProducesCondition();
			final Set<MediaType> mediaTypes = producesRequestCondition.getProducibleMediaTypes();
			Resource wadlResource = null;
			for (final RequestMethod httpMethod : httpMethods) {
				final org.jvnet.ws.wadl.Method wadlMethod = new org.jvnet.ws.wadl.Method();

				for (final String uri : pattern) {
					wadlResource = createOrFind(uri, wadResources);
					wadlResource.setPath(uri);
				}

				wadlMethod.setName(httpMethod.name());
				final Method javaMethod = handlerMethod.getMethod();
				wadlMethod.setId(javaMethod.getName());
				final Doc wadlDocMethod = new Doc();
				wadlDocMethod.setTitle(javaMethod.getDeclaringClass().getSimpleName() + "." + javaMethod.getName());
				wadlMethod.getDoc().add(wadlDocMethod);

				// Request
				final Request wadlRequest = new Request();

				final Annotation[][] annotations = javaMethod.getParameterAnnotations();
				final Class<?>[] paramTypes = javaMethod.getParameterTypes();
				final Parameter[] params = javaMethod.getParameters();
				int i = 0;
				for (final Annotation[] annotation : annotations) {
					final Class<?> paramType = paramTypes[i];
					i++;
					for (final Annotation annotation2 : annotation) {

						if (annotation2 instanceof RequestParam) {
							final RequestParam param2 = (RequestParam) annotation2;
							final Param waldParam = new Param();
							final QName nm = convertJavaToXMLType(paramType);
							// waldParam.setName(param2.value());
							waldParam.setName(params[i].getName());
							waldParam.setStyle(ParamStyle.QUERY);
							waldParam.setRequired(param2.required());
							final String defaultValue = cleanDefault(param2.defaultValue());
							if (!defaultValue.equals("")) {
								waldParam.setDefault(defaultValue);
							}
							waldParam.setType(nm);
							wadlRequest.getParam().add(waldParam);
						} else if (annotation2 instanceof PathVariable) {
							final PathVariable param2 = (PathVariable) annotation2;
							final QName nm = convertJavaToXMLType(paramType);
							final Param waldParam = new Param();
							waldParam.setName(param2.value());
							waldParam.setStyle(ParamStyle.TEMPLATE);
							waldParam.setRequired(true);
							wadlRequest.getParam().add(waldParam);
							waldParam.setType(nm);
						}
					}
				}
				if (!wadlRequest.getParam().isEmpty()) {
					wadlMethod.setRequest(wadlRequest);
				}

				// Response
				if (!mediaTypes.isEmpty()) {
					final Response wadlResponse = new Response();
					final Class methodReturn = handlerMethod.getReturnType().getClass();
					final ResponseStatus status = handlerMethod.getMethodAnnotation(ResponseStatus.class);
					if (status == null) {
						wadlResponse.getStatus().add((long) (HttpStatus.OK.value()));
					} else {
						final HttpStatus httpcode = status.value();
						wadlResponse.getStatus().add((long) httpcode.value());
					}

					for (final MediaType mediaType : mediaTypes) {
						final Representation wadlRepresentation = new Representation();
						wadlRepresentation.setMediaType(mediaType.toString());
						wadlResponse.getRepresentation().add(wadlRepresentation);
					}
					wadlMethod.getResponse().add(wadlResponse);
				}

				wadlResource.getMethodOrResource().add(wadlMethod);

			}

		}
		result.getResources().add(wadResources);

		return result;
	}

	private QName convertJavaToXMLType(final Class<?> type) {
		// QName nm = new QName("");
		QName nm = new QName(xs_namespace, "string", "xs");
		final String classname = type.toString();
		if (classname.indexOf("String") >= 0) {
			nm = new QName(xs_namespace, "string", "xs");

		} else if (classname.indexOf("Integer") >= 0) {
			nm = new QName(xs_namespace, "int", "xs");
		}
		return nm;
	}

	private Resource createOrFind(final String uri, final Resources wadResources) {
		final List<Resource> current = wadResources.getResource();
		for (final Resource resource : current) {
			if (resource.getPath().equalsIgnoreCase(uri)) {
				return resource;
			}
		}
		final Resource wadlResource = new Resource();
		current.add(wadlResource);
		return wadlResource;
	}

	private String getBaseUrl(final HttpServletRequest request) {
		final String requestUri = request.getRequestURI();
		// return request.getScheme() + "://" + request.getServerName() + ":" +
		// request.getServerPort() + requestUri;
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

	private String cleanDefault(String value) {
		value = value.replaceAll("\t", "");
		value = value.replaceAll("\n", "");
		return value;
	}
}