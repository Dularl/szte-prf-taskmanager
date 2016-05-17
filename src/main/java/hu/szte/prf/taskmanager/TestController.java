package hu.szte.prf.taskmanager;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/users")
@RestController
public class TestController {
	private static final Logger log = Logger.getLogger(TestController.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView reset(HttpServletResponse response) throws Exception {
		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		jdbcTemplate.execute("DROP TABLE resource IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE resource(" + "id SERIAL, resource_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());

		List<Object[]> resources = Arrays.asList("Electricity", "Paper", "Human").stream().map(name -> name.split(" "))
				.collect(Collectors.toList());
		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
		resources.forEach(name -> log.info(String.format("Inserting resource record for %s", name[0])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
		jdbcTemplate.batchUpdate("INSERT INTO resource(resource_name) VALUES (?)", resources);

		// response.sendRedirect("/#/asd");
		return new ModelAndView("home.html");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String id) {
		log.info("DELETING:" + id);
		jdbcTemplate.update("DELETE FROM customers WHERE id=?;", id);
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public String getCustomers() {
		// Split up the array of whole names into an array of first/last names
		final List<Customer> customers = new ArrayList<Customer>();
		log.info("Querying for customer records where first_name = 'Josh':");
		jdbcTemplate
				.query("SELECT id, first_name, last_name FROM customers", (rs, rowNum) -> new Customer(rs.getLong("id"),
						rs.getString("first_name"), rs.getString("last_name")))
				.forEach(customer -> customers.add(customer));
		JSONArray json = new JSONArray(customers);
		// json.append("users", new JSONObject(customers));

		return json.toString();
	}

	@RequestMapping(value = "/getResources", method = RequestMethod.GET)
	public String getResources() {
		// Split up the array of whole names into an array of first/last names
		final List<Resource> resources = new ArrayList<Resource>();
		log.info("Querying fo rresources:");
		jdbcTemplate
				.query("SELECT id, resource_name FROM resource",
						(rs, rowNum) -> new Resource(rs.getLong("id"), rs.getString("resource_name")))
				.forEach(customer -> resources.add(customer));
		JSONArray json = new JSONArray(resources);
		// json.append("users", new JSONObject(customers));

		return json.toString();
	}

	@RequestMapping({ "", "/", "/#" })
	@ResponseBody
	public void asd(HttpServletResponse response) throws Exception {
		log.info("REDORECTING");
		response.sendRedirect("home.html");
	}
}
