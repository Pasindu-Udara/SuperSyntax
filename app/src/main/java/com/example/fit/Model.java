package com.example.fit;

public class Model {

    String id, name, breakfast, lunch, tea, dinner;
    public Model(){}

    public Model(String id, String name, String breakfast, String lunch, String tea, String dinner) {
        this.id = id;
        this.name = name;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.tea = tea;
        this.dinner = dinner;
    }

    public String getId() {
        return id;
    }

    public String getTea() {
        return tea;
    }

    public void setTea(String tea) {
        this.tea = tea;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
