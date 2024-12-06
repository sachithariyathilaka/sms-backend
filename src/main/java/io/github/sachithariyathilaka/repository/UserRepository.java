package io.github.sachithariyathilaka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.sachithariyathilaka.entity.User;

/**
 * User repository interface.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
    User findById(int id);
    User deleteById(int id);
    User findByUsernameAndPosition(String Username, String Position);

}
