package kz.yerbolov.MyJavaProject.services;

import kz.yerbolov.MyJavaProject.entites.Permission;
import kz.yerbolov.MyJavaProject.entites.Users;
import kz.yerbolov.MyJavaProject.repositories.PermissionRepository;
import kz.yerbolov.MyJavaProject.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findAllByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

    public String signUpUser(String fullName, String email, String password, String rePassword) {
        String flag = "userExists";
        Users checkUser = usersRepository.findAllByEmail(email);
        if(checkUser == null){
            flag = "passwordNotMatch";
            if(password.equals(rePassword)){
                Permission permission = permissionRepository.findAllByRole("ROLE_USER");
                List <Permission> permissionList = new ArrayList<>();
                permissionList.add(permission);
                Users user = new Users();
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setPermissions(permissionList);
                usersRepository.save(user);
                flag = "successRegister";
            }
        }
        return flag;
    }
}
