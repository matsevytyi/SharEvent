package ENTITY;


public class UserFactory implements UserFactoryInterface {

    // should this be override?
    public UserInterface create(String name, String password, String email, int userId) {
        return new User(name, password, email, userId);
    }
}
