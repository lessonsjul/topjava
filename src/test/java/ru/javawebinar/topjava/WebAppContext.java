package ru.javawebinar.topjava;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceImpl;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

/**
 * Created by yulia on 23.06.16.
 */
@Configuration
@ComponentScan(basePackages = {
            "ru.javawebinar.topjava.repository.mock"
    })
public class WebAppContext {

    @Bean
    public AdminRestController adminRestController(){
        return new AdminRestController();
    }

    @Bean
    public UserMealRestController userMealRestController(){
        return new UserMealRestController();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public UserMealService userMealService(){
        return new UserMealServiceImpl();
    }
}
