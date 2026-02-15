package com.moviebooking.userservice.service;

import com.moviebooking.userservice.exception.UserNotFoundException;
import com.moviebooking.userservice.model.UserPrincipal;
import com.moviebooking.userservice.model.Users;
import com.moviebooking.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        }
        
        return new UserPrincipal(user);
    }
}
