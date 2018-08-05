package model;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Model {

    private static Category log = Logger.getLogger(Model.class);

    private static ThreadLocal<List<User>> users = ThreadLocal.withInitial(ArrayList::new);

    public static void empty(){
        users.set(new ArrayList<>());
    }

    public static List<User> getUsers() { return users.get(); }
    public static void setUsers(List<User> users) {Model.users.set(users); }
    public static User getUser() { return getUsers().get(getUsers().size() - 1); }
    public static void setUser(User user) { getUsers().add(user); }
}
