package com.cs353.backend.service;

import com.cs353.backend.dao.ApplicationDao;
import com.cs353.backend.dao.CertificateDao;
import com.cs353.backend.dao.RegularUserDao;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class RegularUserService {
    private final RegularUserDao regularUserDao;
    private final UserService userService;
    private final PostService postService;
    private GeneralMapper generalMapper;
    private CertificateDao certificateDao;
    private ApplicationDao applicationDao;

    public int createRegularUser(RegularUser regularUser) {
        User user = generalMapper.mapRegularUserToUser(regularUser);
        int id = userService.createUser(user);
        regularUser.setId(id);
        regularUserDao.createRegularUser(regularUser);
        return id;
    }
    public void update(int userId, RegularUser regularUser) {
        regularUser.setId(userId);
        User user = generalMapper.mapRegularUserToUser(regularUser);
        user.setId(userId);
        userService.updateUser(user);
        regularUserDao.updateRegularUser(regularUser);
    }
    public RegularUser getRegularUser(int userId) {
        return regularUserDao.getRegularUserById(userId);
    }
    public List<RegularUser> getAllRegularUsers() {
        return regularUserDao.getAllRegularUsers();
    }

    public void initialBuild() {
        List<String> turkishFirstNames = Arrays.asList(
                "Ahmet", "Mehmet", "Mustafa", "Ali", "Murat", "Ömer","Eren", "Yasin", "Can", "Yusuf",
                "Hüseyin", "Hasan", "İbrahim", "Sultan", "Emine", "Hatice", "Zeynep", "Elif","Ayşe", "Fatma", "Merve", "Ceren", "Ebru", "Deniz" );

        List<String> turkishLastNames = Arrays.asList(
                "Yılmaz", "Demir", "Çelik", "Kaya", "Öztürk", "Arslan", "Doğan", "Şahin", "Demirci", "Acar", "Bulut", "Akça",
                "Aydın", "Aslan", "Baş", "Erdoğan", "Kurt", "Uçar", "Kaplan", "Bulut", "Tekin", "Karadağ", "Ergün", "Ünal");

        for (int i = 5; i < 15; i++) {
            RegularUser user = new RegularUser();
            user.setFirstName(turkishFirstNames.get(i));
            user.setLastName(turkishLastNames.get(i));
            user.setEmail(user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase() + "@hotmail.com");
            user.setPassword("pw");
            user.setAddress("Bilkent University " + (i+1) + ". Dormitory");
            user.setBirthdate(new Date());
            if( i < 13){
                user.setGender("Male");
            }
            else{
                user.setGender("Female");
            }
            user.setPhoneNumber("0555555555" + (i%10));
            user.setProfileDescription("Hello, my name is " + user.getFirstName() + ", and I am looking for job.");

            // Create the regular user and get the user ID
            int userId = this.createRegularUser(user);

            // Create a post for the user
            Post post = new Post();
            post.setUserId(userId);
            post.setPhotoLink("https://example.com/photo.jpg");
            post.setExplanation("This is a post created by " + user.getFirstName());
            post.setHeading("Post Heading");

            // Generate a random date between February 1 and February 28
            int dayOfMonth = i + 1;  // User index + 1 for day of month
            int month = 2;  // February
            int year = 2022;  // Year can be changed as needed

            LocalDateTime dateTime = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
            post.setDate(dateTime);

            // Create the post
            postService.createPost(post, userId);
        }
    }

    public List<Certificate> getCertificates(int userId){
        return certificateDao.getCertificates(userId);
    }

    public void addCertificate(Certificate certificate){
        certificateDao.addCertificate(certificate);
    }

    public List<ApplicationDTO> getMyApplications(int userId){
        return applicationDao.getMyApplications(userId);
    }


}
