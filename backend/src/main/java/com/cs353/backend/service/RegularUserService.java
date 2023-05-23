package com.cs353.backend.service;

import com.cs353.backend.dao.RegularUserDao;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.Post;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.model.entities.User;
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
    private GeneralMapper generalMapper;

    public int createRegularUser(RegularUser regularUser) {
        User user = generalMapper.mapRegularUserToUser(regularUser);
        int id = userService.createUser(user);
        regularUser.setId(id);
        regularUserDao.createRegularUser(regularUser);
        return id;
    }

    public List<RegularUser> getAllRegularUsers() {
        return regularUserDao.getAllRegularUsers();
    }

    public void initialBuild() {
        List<String> turkishFirstNames = Arrays.asList(
                "Ahmet", "Mehmet", "Mustafa", "Ali", "Ayşe", "Fatma", "Emine", "Hatice", "Zeynep", "Elif", "Murat", "Ömer",
                "Hüseyin", "Hasan", "İbrahim", "Sultan", "Yusuf", "Merve", "Ceren", "Ebru", "Yasin", "Can", "Deniz", "Eren");

        List<String> turkishLastNames = Arrays.asList(
                "Yılmaz", "Demir", "Çelik", "Kaya", "Öztürk", "Arslan", "Doğan", "Şahin", "Demirci", "Acar", "Bulut", "Akça",
                "Aydın", "Aslan", "Baş", "Erdoğan", "Kurt", "Uçar", "Kaplan", "Bulut", "Tekin", "Karadağ", "Ergün", "Ünal");

        for (int i = 0; i < 10; i++) {
            RegularUser user = new RegularUser();
            user.setFirstName(turkishFirstNames.get(i));
            user.setLastName(turkishLastNames.get(i));
            user.setEmail(user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase() + "@hotmail.com");
            user.setPassword("pw");
            user.setAddress("");
            user.setBirthdate(new Date());
            user.setGender("");
            user.setPhoneNumber("");
            user.setProfileDescription("");

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
            userService.createPost(post, userId);
        }
    }
}
