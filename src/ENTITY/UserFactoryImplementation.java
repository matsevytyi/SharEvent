package ENTITY;


public class UserFactoryImplementation implements UserFactory{
    @Override
    public User create(String username, String name, String email, String password) {
        return new User(username, name, email, password);
    }
}
