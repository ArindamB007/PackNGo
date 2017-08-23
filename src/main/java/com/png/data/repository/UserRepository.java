package com.png.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.png.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail (String email);
	User findByIdUser (Long id_user);
}
