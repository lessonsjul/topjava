package ru.javawebinar.topjava.store;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by yulia on 12.06.16.
 * This class realize storing user meal list with id
 * Also we can add, update, read and remove userMeal using this class
 * This class is made as singleton without lazy initialization
 */

public class UserMealCashe implements UserMealStore{

    private static final UserMealCashe instance = new UserMealCashe();
    private static final ConcurrentHashMap<Integer, UserMeal> userMeals = new ConcurrentHashMap<>();
    private static AtomicInteger idx = new AtomicInteger(0);

    private UserMealCashe() {
    }

    public static UserMealCashe getInstance(){
        return instance;
    }

    static {
        for(UserMeal um: UserMealsUtil.MEAL_LIST)
            getInstance().save(um);
    }

    @Override
    public Collection<UserMeal> values() { return this.userMeals.values(); }

    @Override
    public UserMeal get(int id) { return this.userMeals.get(id); }

    @Override
    public UserMeal save(UserMeal um) {
        if(um.isNew()){
            um.setId(idx.incrementAndGet());
        }
        return userMeals.put(um.getId(),um);
    }

    @Override
    public void delete(int id){
        this.userMeals.remove(id);
    }
}
