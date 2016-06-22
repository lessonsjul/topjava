package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal, int userId);

    //false if not found or doesn't belong user
    boolean delete(int id, int idUser);

    // null if not found
    UserMeal get(int id, int idUser);

    //empty if not found
    List<UserMeal> getAll(int idUser);

    List<UserMeal> getBeetwen(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int idUser);

    boolean deleteAll(int userId);
}
