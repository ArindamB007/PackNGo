package com.png.data.repository;

import org.springframework.stereotype.Repository;

import com.png.data.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
