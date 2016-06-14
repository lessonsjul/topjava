package ru.javawebinar.topjava.model;

/**
 * Created by yulia on 12.06.16.
 */
public class Model {

    protected Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                '}';
    }
}
