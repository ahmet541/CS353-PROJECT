package com.cs353.backend.service;

import com.cs353.backend.dao.RegularUserDao;
import com.cs353.backend.mapper.GeneralMapper;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.RegularUser;
import com.cs353.backend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

            this.createRegularUser(user);
        }
    }
}
