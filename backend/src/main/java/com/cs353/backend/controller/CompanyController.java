package com.cs353.backend.controller;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Company;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.service.AccountService;
import com.cs353.backend.service.CompanyService;
import com.cs353.backend.service.RegularUserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    /*@GetMapping("getAll")
    public ResponseEntity<List<RegularUser>> getAllRegularUsers() {
        List<RegularUser> regularUsers = regularUserService.getAllRegularUsers();
        return new ResponseEntity<>(regularUsers, HttpStatus.OK);
    }*/

    //    @GetMapping("/{id}")
//    public ResponseEntity<RegularUser> getAccountById(@PathVariable int id) {
//
//    }
//
    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody Company company) {
        try{
            companyService.createCompany(company);
            return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateAccount(@PathVariable int id, @RequestBody @NotNull RegularUser regularUser) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
//
//    }
}
