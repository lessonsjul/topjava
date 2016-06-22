package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.to.UserMealWithExceed;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public UserMeal get(int id){
        int userId = LoggedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public UserMeal create(UserMeal userMeal){
        int userId = LoggedUser.id();
        LOG.info("save meal {} for User {}", userMeal, userId);
        return service.save(userMeal, userId);
    }

    public void delete(int id){
        int userId = LoggedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<UserMealWithExceed> getAll(){
        int userId = LoggedUser.id();
        LOG.info("getAll meals for User {}", userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId),LoggedUser.get().getCaloriesPerDay());
    }

    public void deleteAll(){
        int userId = LoggedUser.id();
        LOG.info("getAll meals for User {}", userId);
        service.deleteAll(userId);
    }

    public List<UserMealWithExceed> getBeetwen(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        int userId = LoggedUser.id();
        if(startDate == null) startDate = LocalDate.MIN;
        if(endDate == null) endDate = LocalDate.MAX;
        if(startTime == null) startTime = LocalTime.MIN;
        if(endTime == null) endTime = LocalTime.MAX;
        LOG.info("get meals beetwen {} {} to {} {} for User {}", startDate, startTime, endDate, endTime, userId);
        return UserMealsUtil.getWithExceeded(service.getBeetwen(startDate, endDate, startTime, endTime, userId), LoggedUser.get().getCaloriesPerDay());
    }

    public void update(UserMeal userMeal){
        int userId = LoggedUser.id();
        LOG.info("update meals {} for User {}", userMeal, userId);

    }
}