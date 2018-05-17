package tech.diggle.apps.accomodation.user;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}