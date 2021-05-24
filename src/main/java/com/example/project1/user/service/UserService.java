package com.example.project1.user.service;

import com.example.project1.user.Role;
import com.example.project1.user.Usr;
import com.example.project1.user.config.emailToken.ConfirmationToken;
import com.example.project1.user.config.emailToken.EmailToken;
import com.example.project1.user.config.emailToken.emailSender.EmailSender;
import com.example.project1.user.repos.UserRepository;
import com.example.project1.user.repos.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService { 

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationToken confirmationToken;

    @Autowired
    private EmailSender emailSender;
    
    @Autowired
    private UserDetailsRepository userDetailsRepository;


    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usr user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addNewUser(Usr usr) {
        Usr userFromDb = userRepository.findByUsername(usr.getUsername());
        Usr emailChek=userRepository.findUsrByEmail(usr.getEmail());

        if (userFromDb!=null) {
            return false;
        }
        else if(emailChek!=null){
            return false;
        }


            usr.setRole(Role.USER);
            usr.setEmail(usr.getEmail());
            usr.setUsername(usr.getUsername());
            usr.setPassword(passwordEncoder.encode(usr.getPassword()));

            userRepository.save(usr);
        EmailToken token = new EmailToken(usr);

        confirmationToken.save(token);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usr.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("tourbooking@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirmaccount?token="+token.getToken());

        emailSender.sendEmail(mailMessage);

        return true;
    }

    public void deleteusr(Usr usr){
        userRepository.delete(usr);
    }
    
    public void addPersonalInfo(Long id, com.example.project1.user.UserDetails userDetails){
        userDetails.setUsr(userRepository.findUsrById(id));
        userDetailsRepository.save(userDetails);
    }

    public void updateUsername(String username,Long id){
        userRepository.updateUsername(username,id);
    }

    public boolean updatePassword(String oldPassword,String newPassword,Usr usr){
        if(!(passwordEncoder.matches(oldPassword,usr.getPassword()))){
            return false;
        }
        if(oldPassword==newPassword){
            return false;
        }
        usr.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(usr);
        return true;
    }


}
