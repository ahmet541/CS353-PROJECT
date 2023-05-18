package com.cs353.backend.controller;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.service.AccountService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @GetMapping("getAll")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable int id, @RequestBody @NotNull Account account) {
        account.setId(id);
        accountService.updateAccount(account);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}
