package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    {
        save(new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500), USER_ID);
        save(new UserMeal(2,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000), USER_ID);
        save(new UserMeal(3,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500), USER_ID);
        save(new UserMeal(4,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000), USER_ID);
        save(new UserMeal(5,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500), USER_ID);
        save(new UserMeal(6,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), USER_ID);

        save(new UserMeal(7,LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), ADMIN_ID);
        save(new UserMeal(8,LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 510), ADMIN_ID);
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        Integer mealId = userMeal.getId();

        if(userMeal.isNew()){
            userMeal.setId(counter.incrementAndGet());
        }else if(get(mealId, userId) == null){
            return null;
        }

        Map<Integer, UserMeal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        meals.put(mealId, userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int idUser) {
        Map<Integer, UserMeal> userMeals = repository.get(idUser);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public UserMeal get(int id, int idUser) {
        Map<Integer, UserMeal> userMeals = repository.get(idUser);
        return userMeals == null ? null : userMeals.get(id);
    }

    @Override
    public List<UserMeal> getAll(int idUser) {
        return repository.get(idUser).values().stream().sorted(UserMeal.COMPARE_BY_DATE).collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> getBeetwen(LocalDateTime startDate, LocalDateTime endDate, int idUser) {
        return repository.get(idUser).values().stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime(),startDate,endDate))
                .sorted(UserMeal.COMPARE_BY_DATE)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll(int userId) {
        return repository.remove(userId) != null;
    }
}

