package com.nishjournal.Nishjournal.service;

import com.nishjournal.Nishjournal.entry.User;
import com.nishjournal.Nishjournal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {
    public static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    public UserRepository userRepository;

    public void createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);

    }

    public boolean saveNewUser(User user){
          try{
              user.setPassword(passwordEncoder.encode(user.getPassword()));
              user.setRoles(Arrays.asList("USER"));
              userRepository.save(user);
              return true;
          } catch (Exception e){
              log.info("hey this is the exception"+e);
              return false;
          }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
