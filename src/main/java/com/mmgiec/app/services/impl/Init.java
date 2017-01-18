package com.mmgiec.app.services.impl;

import com.mmgiec.app.entities.Phone;
import com.mmgiec.app.entities.Role;
import com.mmgiec.app.entities.User;
import com.mmgiec.app.entities.Warehouse;
import com.mmgiec.app.repositories.WarehouseRepository;
import com.mmgiec.app.services.PhoneService;
import com.mmgiec.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class Init {
    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostConstruct
    public void init(){
        ConverterJSON converterJSON = new ConverterJSON();
        try {
            List<Phone> phones = converterJSON.convertToList();
            if (phones != null) {
                if (!phones.isEmpty()) {
                    for (Phone p : phones) {
                        phoneService.save(p);
                        System.out.println(p.getId());
                        Warehouse warehouse = new Warehouse(p.getId(), 2000);
                        warehouseRepository.save(warehouse);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<User> user = userService.findByEmail("admin@gmail.com");
        if(!user.isPresent()){
            User userAdmin = new User();
            userAdmin.setFirst_name("Admin");
            userAdmin.setLast_name("Admin");
            userAdmin.setPassword("admin");
            userAdmin.setEmail("admin@gmail.com");
            userAdmin.setRole(Role.ROLE_ADMIN);
            userService.save(userAdmin);
        }

        Optional<User> user2 = userService.findByEmail("user@gmail.com");
        if(!user2.isPresent()){
            User regularUser = new User();
            regularUser.setFirst_name("User");
            regularUser.setLast_name("User");
            regularUser.setPassword("user");
            regularUser.setEmail("user@gmail.com");
            regularUser.setRole(Role.ROLE_USER);
            userService.save(regularUser);
        }
    }
}
