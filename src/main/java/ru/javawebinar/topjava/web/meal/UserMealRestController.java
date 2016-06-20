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

import java.time.LocalDateTime;
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
        return UserMealsUtil.getWithExceeded(service.getAll(userId),UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void deleteAll(){
        int userId = LoggedUser.id();
        LOG.info("getAll meals for User {}", userId);
        service.deleteAll(userId);
    }

    public List<UserMeal> getBeetwen(LocalDateTime startDate, LocalDateTime endDate){
        int userId = LoggedUser.id();
        LOG.info("get meals beetwen {} {} for User {}", startDate, endDate, userId);
        return service.getBeetwen(startDate, endDate, userId);
    }

    public void update(UserMeal userMeal){
        int userId = LoggedUser.id();
        LOG.info("update meals {} for User {}", userMeal, userId);

    }
}
