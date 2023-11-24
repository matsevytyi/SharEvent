package ENTITY;

public interface UserFactoryInterface {

    UserInterface create(String name, String password, String email, int userId);

}