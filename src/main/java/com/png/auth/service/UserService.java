package com.png.auth.service;

import com.png.data.entity.User;

public interface UserService {
	void save(User user);

    User findByUsername(String username);
}
