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
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yulia on 12.06.16.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final UserMealCashe USER_MEAL_CASHE = UserMealCashe.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null) {
            LOG.info("values()");
            req.setAttribute("mealList", UserMealsUtil.convertWithExceeded(USER_MEAL_CASHE.values(), 2000));
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }else if(action.equals("delete")){
            int id = Integer.valueOf(req.getParameter("id"));
            LOG.info("Delete {}",id);
            this.USER_MEAL_CASHE.delete(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/meals"));
        }else{
            LOG.info("redirect to mealUpdate");
            UserMeal um = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(),"",1000):
                    USER_MEAL_CASHE.get(getId(req));

            req.setAttribute("userMeal",um);
            req.getRequestDispatcher("mealUpdate.jsp").forward(req, resp);
        }
    }

    private int getId(HttpServletRequest req) {
        String param = Objects.requireNonNull(req.getParameter("id"));
        return Integer.valueOf(param);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        UserMeal um = new UserMeal(
                id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime"), TimeUtil.DATE_TIME_FORMATTER),
                String.valueOf(req.getParameter("description")),
                Integer.valueOf(req.getParameter("calories"))
        );

        this.USER_MEAL_CASHE.save(um);
        LOG.info(um.isNew() ? "Create {}" : "Update {}", um);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/meals"));
    }
}
