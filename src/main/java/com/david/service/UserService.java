package com.david.service;

import com.david.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    User updateName(Long id, String name);
    User updateCard(Long id, String cardNumber, java.math.BigDecimal limit);
    User updateAccount(Long id, String number, String agency, java.math.BigDecimal limit);
}
