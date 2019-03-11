package br.com.devcave.jwt.repository;

import br.com.devcave.jwt.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
