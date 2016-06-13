package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.store.UserMealCashe;
import ru.javawebinar.topjava.util.TimeUtil;

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
public class MealDeleteServlet extends HttpServlet{
    private static final Logger LOG = getLogger(UserServlet.class);
    private static final UserMealCashe USER_MEAL_CASHE = UserMealCashe.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("delete meal and redirect to MealList");

        this.USER_MEAL_CASHE.delete(Integer.valueOf(req.getParameter("id")));

        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/meals"));
    }
}
