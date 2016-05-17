package hu.szte.prf.taskmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Hibernate;

@Entity
@Table(name = "resource_request", uniqueConstraints = {@UniqueConstraint(name = "unique_resource_request", columnNames = {"resource_id", "task_id"})})
public class ResourceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	private boolean granted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isGranted() {
		return granted;
	}

	public void setGranted(boolean granted) {
		this.granted = granted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getResource() == null) ? 0 : getResource().hashCode());
		result = prime * result + ((getTask() == null) ? 0 : getTask().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;
		ResourceRequest other = (ResourceRequest) obj;
		if (getResource() == null) {
			if (other.getResource() != null)
				return false;
		} else if (!getResource().equals(other.getResource()))
			return false;
		if (getTask() == null) {
			if (other.getTask() != null)
				return false;
		} else if (!getTask().equals(other.getTask()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResourceRequest [id=" + getId() + ", resource=" + getResource() + ", task=" + getTask() + ", granted=" + isGranted() + "]";
	}
	
	
}
