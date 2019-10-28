package com.threetee.formationSpring.dao;

import java.util.List;
import java.util.Optional;

import com.threetee.formationSpring.model.entity.AbstractEntity;

public interface IAbstractDao<E extends AbstractEntity> {

	E save(E entity);

	void delete(Long id);

	E update(E entity);

	Optional<E> findById(Long id);

	List<E> findAll();

}
