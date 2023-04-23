package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable{
    private static final Model instance = new Model();

    private final Map<Integer, User> model;


    public static Model getInstance(){
        return  instance;
    }

    private Model() {
        model = new HashMap<>();


        model.put(1, new User("Ann", "Safronova", 5555));
        model.put(2, new User("Anton", "Osipov", 6666));
        model.put(3, new User("Zoya", "Andreeva", 7777));
        model.put(4, new User("Elena", "Vorobey", 8888));
    }

    public void add(User user, int id){
        model.put(id, user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

    public void delete(int id){
        model.remove(id);
    }

    public void put(User user, int id) {
    }
}
