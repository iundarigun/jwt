package br.com.devcave.jwt.repository;

import br.com.devcave.jwt.domain.Role;
import br.com.devcave.jwt.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(String role);
}
