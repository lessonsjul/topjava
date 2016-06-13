package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.store.UserMealCashe;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yulia on 12.06.16.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(UserServlet.class);
    private static final UserMealCashe USER_MEAL_CASHE = UserMealCashe.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        List<UserMealWithExceed> mealsWithExceeded =
                UserMealsUtil.convertToUserMealWithExceeded( USER_MEAL_CASHE.values(), 2000);

        req.setAttribute("mealList",mealsWithExceeded);
        req.getRequestDispatcher("mealList.jsp").forward(req,resp);
    }
}
