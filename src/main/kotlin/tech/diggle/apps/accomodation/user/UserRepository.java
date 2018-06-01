package tech.diggle.apps.accomodation.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findById(int userId);

}