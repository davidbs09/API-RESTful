package com.david.controller;

import com.david.domain.model.User;
import com.david.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<User> updateName(@PathVariable Long id, @RequestParam String name) {
        var user = userService.updateName(id, name);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/card")
    public ResponseEntity<User> updateCard(@PathVariable Long id, @RequestParam String cardNumber, @RequestParam java.math.BigDecimal limit) {
        var user = userService.updateCard(id, cardNumber, limit);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/account")
    public ResponseEntity<User> updateAccount(@PathVariable Long id,
                                              @RequestParam(required = false) String number,
                                              @RequestParam(required = false) String agency,
                                              @RequestParam(required = false) java.math.BigDecimal limit) {
        var user = userService.updateAccount(id, number, agency, limit);
        return ResponseEntity.ok(user);
    }
}
