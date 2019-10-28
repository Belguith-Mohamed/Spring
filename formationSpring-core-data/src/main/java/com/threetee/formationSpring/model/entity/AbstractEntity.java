package com.threetee.formationSpring.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	private Long id;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modifiedAt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;

	public AbstractEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractEntity(Long id, Date modifiedAt, Date createdAt) {
		super();
		this.id = id;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
