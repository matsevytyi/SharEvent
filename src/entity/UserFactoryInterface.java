package entity;

public interface UserFactoryInterface {

    UserInterface create(String name, String password, int userId);
}
