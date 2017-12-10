package com.png.data.repository;

import com.png.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail (String email);
	User findByIdUser (Long id_user);
}
