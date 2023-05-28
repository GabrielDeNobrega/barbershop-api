package com.barbershop.application.core.base.classes;

import java.util.Date;
import org.hibernate.annotations.UpdateTimestamp;
import com.barbershop.application.core.base.interfaces.IValidatableEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity<T> implements IValidatableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private Date deletedAt;

	public BaseEntity() { }

	public BaseEntity(Date createdAt) {
		this.createdAt = createdAt;
	}

	public BaseEntity(T id) {
		this.id = id;
		this.createdAt = new Date();
	}

	public T getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void validate() { };
}
