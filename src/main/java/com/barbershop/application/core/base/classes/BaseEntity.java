package com.barbershop.application.core.base.classes;

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
	
	public BaseEntity() { }
	
	public BaseEntity(T id) {
		this.id = id;
	}
	
	public T getId() {
		return id;
	}
	
	public void validate() throws Exception {};
}
