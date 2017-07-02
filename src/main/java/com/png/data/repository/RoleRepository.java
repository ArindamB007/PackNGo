package com.png.data.repository;

import org.springframework.stereotype.Repository;

import com.png.data.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Set <Role> findByName (String name);
}
