package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private ConfigurableApplicationContext springContext;
    private UserMealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        this.mealController = springContext.getBean(UserMealRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        UserMeal userMeal;
        if(id.isEmpty()){
            userMeal = new UserMeal(null,
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            mealController.create(userMeal);
        }else {
            userMeal = new UserMeal(Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            mealController.update(userMeal);
        }
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",this.mealController.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            this.mealController.delete(id);
            response.sendRedirect("meals");
        } else if(action.equals("filter")){
            LocalDate startDate  = request.getParameter("startDate").isEmpty() ? null : LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate  = request.getParameter("endDate").isEmpty() ? null : LocalDate.parse(request.getParameter("endDate"));
            LocalTime startTime  = request.getParameter("startTime").isEmpty() ? null : LocalTime.parse(request.getParameter("startTime"));
            LocalTime endTime  = request.getParameter("endTime").isEmpty() ? null : LocalTime.parse(request.getParameter("endTime"));
            LOG.info("filterd from {} {} to {} {}", startDate, startTime, endDate, endTime);
            request.setAttribute("mealList", this.mealController.getBeetwen(startDate, endDate, startTime, endTime));

            request.setAttribute("startDate",startDate);
            request.setAttribute("endDate",endDate);
            request.setAttribute("startTime",startTime);
            request.setAttribute("endTime",endTime);

            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }else if (action.equals("create") || action.equals("update")) {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000) :
                    mealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
