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
 */
public class UserMealCashe {

    private static final UserMealCashe instance = new UserMealCashe();

    private UserMealCashe() {

    }

    public static UserMealCashe getInstance(){
        return UserMealCashe.instance;
    }

    private static final ConcurrentHashMap<Integer, UserMeal> userMeals = new ConcurrentHashMap<>();

    static{
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        for(UserMeal um: mealList){
            int id = UserMealsUtil.idx.incrementAndGet();
            userMeals.put(id,new UserMeal(id,um.getDateTime(),um.getDescription(),um.getCalories()));
        }
    }

    public Collection<UserMeal> values() { return this.userMeals.values(); }

    public UserMeal get(int id) { return this.userMeals.get(id); }

    public void add(UserMeal um){
        this.userMeals.put(um.getId(),um);
    }

    public void update(UserMeal um){
        this.userMeals.replace(um.getId(), um);
    }

    public void delete(int id){
        this.userMeals.remove(id);
    }
}
