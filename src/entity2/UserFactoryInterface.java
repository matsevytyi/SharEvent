package entity2;

public interface UserFactoryInterface {

    UserInterface create(String name, String password, String email, int userId);

}