package br.com.devcave.jwt.controller;

import br.com.devcave.jwt.domain.entity.Product;
import br.com.devcave.jwt.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/")
    public HttpEntity<List<Product>> getProducts() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/")
    public HttpEntity<?> save(@Valid @RequestBody Product product) {
        productService.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(product.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public HttpEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Long id,@Valid @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.noContent().build();
    }
}
