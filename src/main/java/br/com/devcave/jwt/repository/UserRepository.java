package br.com.devcave.jwt.repository;

import br.com.devcave.jwt.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
