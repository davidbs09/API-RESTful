package com.david.service.impl;

import com.david.domain.model.User;
import com.david.domain.repository.UserRepository;
import com.david.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User updateName(Long id, String name) {
        User user = findById(id);
        user.setName(name);
        return userRepository.save(user);
    }

    @Override
    public User updateCard(Long id, String cardNumber, java.math.BigDecimal limit) {
        User user = findById(id);
        if (user.getCard() == null) {
            throw new IllegalArgumentException("User does not have a card");
        }
        user.getCard().setNumber(cardNumber);
        user.getCard().setLimit(limit);
        return userRepository.save(user);
    }

    @Override
    public User updateAccount(Long id, String number, String agency, java.math.BigDecimal limit) {
        User user = findById(id);
        if (user.getAccount() == null) {
            throw new IllegalArgumentException("User does not have an account");
        }
        if (number != null) user.getAccount().setNumber(number);
        if (agency != null) user.getAccount().setAgency(agency);
        if (limit != null) user.getAccount().setLimit(limit);
        return userRepository.save(user);
    }
}
