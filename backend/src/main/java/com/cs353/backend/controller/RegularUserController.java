package com.cs353.backend.controller;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.JobOpening;
import com.cs353.backend.model.entities.Pair;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.service.AccountService;
import com.cs353.backend.service.JobOpeningService;
import com.cs353.backend.service.RegularUserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regular-user")
@AllArgsConstructor
public class RegularUserController {

    private RegularUserService regularUserService;
    private  JobOpeningService jobOpeningService;
    //private JobOpeningService jobOpeningService;

    @GetMapping("getAll")
    public ResponseEntity<List<RegularUser>> getAllRegularUsers() {
        List<RegularUser> regularUsers = regularUserService.getAllRegularUsers();
        return new ResponseEntity<>(regularUsers, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<RegularUser> getAccountById(@PathVariable int id) {
//
//    }
//
    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody RegularUser regularUser) {
        regularUserService.createRegularUser(regularUser);
        return new ResponseEntity<>("Regular User added successfully", HttpStatus.CREATED);
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

    @GetMapping("myApplications/{userId}")
    public List<Pair> getMyApplications(@PathVariable int userId) {
        List<Pair> pairs = new ArrayList<Pair>();

        List< ApplicationDTO> appList = regularUserService.getMyApplications(userId);
        for(ApplicationDTO app: appList){
            Pair pair = new Pair();
            pair.applicationDTO = app;
            pair.jobOpeningDTO = jobOpeningService.getJobOpeningById(app.getJob_opening_id());
            pairs.add(pair);
        }
        return pairs;

    }


}
