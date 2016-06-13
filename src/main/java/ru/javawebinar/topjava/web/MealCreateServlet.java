package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.store.UserMealCashe;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yulia on 12.06.16.
 */
public class MealCreateServlet extends HttpServlet{
    private static final Logger LOG = getLogger(UserServlet.class);
    private static final UserMealCashe USER_MEAL_CASHE = UserMealCashe.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to create Meals");

        this.USER_MEAL_CASHE.add(
                new UserMeal(
                        UserMealsUtil.idx.incrementAndGet(),
                        LocalDateTime.parse(req.getParameter("dateTime"), TimeUtil.DATE_TIME_FORMATTER),
                        String.valueOf(req.getParameter("description")),
                        Integer.valueOf(req.getParameter("calories"))
                )
        );

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/meals"));
    }
}
