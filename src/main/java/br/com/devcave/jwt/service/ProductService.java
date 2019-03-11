package br.com.devcave.jwt.service;

import br.com.devcave.jwt.domain.entity.Product;
import br.com.devcave.jwt.exception.BadRequestException;
import br.com.devcave.jwt.exception.ItemNotFoundException;
import br.com.devcave.jwt.repository.ProductRepository;
import com.google.common.collect.FluentIterable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return FluentIterable.from(productRepository.findAll()).toList();
    }

    @Transactional
    public void save(Product product) {
        if (product.getId() != null){
            throw new BadRequestException("Não pode informar o id");
        }
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(ItemNotFoundException::new);
    }

    @Transactional
    public void update(Long id, Product product) {
        Product oldProduct = findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(product);
    }
}
