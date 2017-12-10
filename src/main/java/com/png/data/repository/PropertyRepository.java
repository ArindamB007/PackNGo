package com.png.data.repository;

import com.png.data.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
	Property findByIdProperty(Long id_property);
	ArrayList<Property> findAllByEnabledFlagTrue();
}
