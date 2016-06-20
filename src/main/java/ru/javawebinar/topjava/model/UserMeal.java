package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity{

    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    public UserMeal(){

    }

    public UserMeal(LocalDateTime dateTime, String description, int calories){
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories){
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public static Comparator<UserMeal> COMPARE_BY_DATE = new Comparator<UserMeal>() {
        @Override
        public int compare(UserMeal meal, UserMeal other) {
            return other.dateTime.compareTo(meal.dateTime);
        }
    };
}
