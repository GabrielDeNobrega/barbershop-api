package com.barbershop.application.core.base.classes;

import java.util.Date;

import com.barbershop.application.core.base.interfaces.IValidatableEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> implements IValidatableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id;
	private Date createdAt;
	private Date deletedAt; 
	private Date updatedAt; 
	
	public BaseEntity() { }
	
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
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void validate() throws Exception {};
}
