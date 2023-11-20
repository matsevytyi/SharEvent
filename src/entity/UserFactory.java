package entity;


public class UserFactory {

    // should this be override?
    public UserInterface create(String name, String password, int userId) {
        return new User(name, password, userId);
    }
}
