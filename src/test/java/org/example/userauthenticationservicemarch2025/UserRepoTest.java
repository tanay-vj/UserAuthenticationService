package org.example.userauthenticationservicemarch2025;

import org.example.userauthenticationservicemarch2025.models.Role;
import org.example.userauthenticationservicemarch2025.models.User;
import org.example.userauthenticationservicemarch2025.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void insertDummyDataForSpringCloud() {
        User user1 = new User();
        user1.setEmailId("aka");
        user1.setPassword("aka");
        user1.setRole(Role.ADMIN);
        userRepo.save(user1);

        User user2 = new User();
        user2.setEmailId("abc");
        user2.setPassword("abc");
        user2.setRole(Role.ADMIN);
        userRepo.save(user2);
    }
}
