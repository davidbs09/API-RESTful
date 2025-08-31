package com.david.service;

import com.david.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
