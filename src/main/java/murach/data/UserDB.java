package murach.data;

import murach.business.User;
import java.util.ArrayList;

public class UserDB {
    private static ArrayList<User> users = new ArrayList<>();

    public static void insert(User user) {
        users.add(user);
    }

    public static ArrayList<User> selectAll() {
        return users;
    }
}
