package br.com.devcave.jwt;

import br.com.devcave.jwt.domain.entity.Role;
import br.com.devcave.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Autowired
	public void create(RoleRepository roleRepository) {
		if (roleRepository.findByRole("ADMIN") == null){
			roleRepository.save(Role.builder().role("ADMIN").build());
		}
	}
}
