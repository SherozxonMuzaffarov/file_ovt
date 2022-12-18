package com.uzvagontamir.File.service;

import com.uzvagontamir.File.model.Role;
import com.uzvagontamir.File.model.User;
import com.uzvagontamir.File.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    private UserRepository userRepository;

    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//	@Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository){
//		return args ->{
//            User user1 = new User("vchd", "11","vchd-3", Role.USER);
//            User user2 = new User("ot", "ot","ot", Role.USER);
//            User user3 = new User("oe", "oe", "oe", Role.USER);
//            User user4 = new User("ut", "ut", "ut", Role.USER);
//            User user5 = new User("ur", "ur", "ur", Role.USER);
//            User user6 = new User("ue", "ue", "ue", Role.USER);
//            User user7 = new User("uf", "uf", "uf", Role.USER);
//            User user8 = new User("un", "un", "un", Role.USER);
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
//            userRepository.save(user4);
//            userRepository.save(user5);
//            userRepository.save(user6);
//            userRepository.save(user7);
//            userRepository.save(user8);
//        };
//	}
}
