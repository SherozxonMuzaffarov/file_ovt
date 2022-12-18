package com.uzvagontamir.File.service;

import com.uzvagontamir.File.model.CustomUserDetails;
import com.uzvagontamir.File.model.User;
import com.uzvagontamir.File.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Foydalanuvchi topilmadi");
        return new CustomUserDetails(user);
    }
}
