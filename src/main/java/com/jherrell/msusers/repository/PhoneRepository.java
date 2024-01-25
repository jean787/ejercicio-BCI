package com.jherrell.msusers.repository;

import com.jherrell.msusers.model.db.PhoneEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneEntity, Integer> {
}
