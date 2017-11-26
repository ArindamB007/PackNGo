package com.png.data.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.png.data.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
	Property findByIdProperty(Long id_property);
	ArrayList<Property> findAllByEnabledFlagTrue();
}
