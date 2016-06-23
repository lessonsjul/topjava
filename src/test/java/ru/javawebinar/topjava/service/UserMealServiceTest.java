package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by yulia on 23.06.16.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"

})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        UserMeal um = new UserMeal(LocalDateTime.now(),"new Meal",2000);
        UserMeal created = service.save(um, USER_ID);
        um.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(userMeal1,um,userMeal2,userMeal3),service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void tryDeleteForeignMeal(){
        service.delete(MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID_5, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(adminMeal1), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal um = service.get(MEAL_ID_1,USER_ID);
        MATCHER.assertEquals(userMeal1,um);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(userMeal2,userMeal1),
                service.getBetweenDateTimes(LocalDateTime.of(2015,05,30,9,0),LocalDateTime.of(2015,05,31,13,0),USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(userMeal3,userMeal2,userMeal1),
                service.getBetweenDates(LocalDate.of(2015,05,30), LocalDate.of(2015,05,31),USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(userMeal3,userMeal2,userMeal1),service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal umUpdate = userMeal3;
        umUpdate.setCalories(300);
        umUpdate.setDescription("updated");
        service.update(umUpdate, USER_ID);
        MATCHER.assertEquals(umUpdate, service.get(umUpdate.getId(), USER_ID));
    }

}