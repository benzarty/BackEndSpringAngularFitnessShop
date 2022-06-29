package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
