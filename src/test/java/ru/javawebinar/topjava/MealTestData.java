package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;


    public static final int MEAL_ID_1 = START_SEQ + 2;
    public static final int MEAL_ID_2 = START_SEQ + 3;
    public static final int MEAL_ID_3 = START_SEQ + 4;

    public static final int MEAL_ID_4 = START_SEQ + 5;
    public static final int MEAL_ID_5 = START_SEQ + 6;

    public static final UserMeal userMeal1 = new UserMeal(MEAL_ID_1,LocalDateTime.of(2015, 5, 30, 10, 0),"Завтрак", 145);
    public static final UserMeal userMeal2 = new UserMeal(MEAL_ID_2,LocalDateTime.of(2015, 5, 30, 13, 0),"Обед", 500);
    public static final UserMeal userMeal3 = new UserMeal(MEAL_ID_3,LocalDateTime.of(2015, 5, 30, 20, 0),"Ужин", 700);

    public static final UserMeal adminMeal1 = new UserMeal(MEAL_ID_4,LocalDateTime.of(2015, 5, 31, 11, 0),"Затрак админа", 145);
    public static final UserMeal adminMeal2 = new UserMeal(MEAL_ID_5,LocalDateTime.of(2015, 5, 31, 15, 0),"Admin lunch", 500);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
