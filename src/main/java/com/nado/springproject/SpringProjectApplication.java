package com.nado.springproject;

import com.nado.dao.PermissionGroupRepo;
import com.nado.dao.PermissionRepo;
import com.nado.model.Permission;
import com.nado.model.PermissionGroup;
import com.nado.model.PermissionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    private PermissionRepo permissionRepo;

    private PermissionGroupRepo groupRepository;

    @Override
    public void run(String... arg0) throws Exception {


        // init permissions
        if (permissionRepo.findAll().isEmpty()) {
            PermissionGroup admin = new PermissionGroup();
            admin.setGroupName("admin");
            groupRepository.save(admin);

            PermissionGroup user = new PermissionGroup();
            user.setGroupName("user");
            groupRepository.save(user);

            Permission per1 = new Permission();
            per1.setGroup(admin);
            per1.setPermissionLevel(PermissionLevel.VIEW);
            per1.setUserEmail("a  dmin-view@stc.com");
            permissionRepo.save(per1);

            Permission per2 = new Permission();
            per2.setGroup(admin);
            per2.setPermissionLevel(PermissionLevel.EDIT);
            per2.setUserEmail("admin-edit@stc.com");
            permissionRepo.save(per2);

            Permission per3 = new Permission();
            per3.setGroup(user);
            per3.setPermissionLevel(PermissionLevel.VIEW);
            per3.setUserEmail("user-view@stc.com");
            permissionRepo.save(per3);

            Permission per4 = new Permission();
            per4.setGroup(admin);
            per4.setPermissionLevel(PermissionLevel.DOWNLOAD);
            per4.setUserEmail("admin-download@stc.com");
            permissionRepo.save(per4);
        }

    }
}
