package com.example.springboot.controller;

import com.example.springboot.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Manoel Campos
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Set<Product> products = new HashSet<>(
            Set.of(new Product("Computador", 19.99),
                    new Product("Mouse", 29.99),
                    new Product("Teclado", 109.99),
                    new Product("Monitor", 1200.99),
                    new Product("Impressora", 500.99))
    );

    @GetMapping
    public Set<Product> all() {
        return products;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        return products.stream()
                       .filter(p -> p.getId() == id)
                       .findFirst()
                       .orElseThrow(() -> newNotFoundException(id));
    }

    @GetMapping("/sorted")
    public List<Product> findById(@RequestParam(required = false) String order) {
        final String orderParam = order == null ? "" : order.toLowerCase();
        if(orderParam.isBlank()) {
            return products.stream().toList();
        }

        final var comparator = Comparator.comparingDouble(Product::getPrice);
        if(orderParam.equals("asc")) {
            return products.stream().sorted(comparator).toList();
        } else if(orderParam.equals("desc")) {
            return products.stream().sorted(comparator.reversed()).toList();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parâmetro 'order' inválido. Use 'asc' ou 'desc'.");
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        System.out.println(product);
        products.add(product);
        return product;
    }

    /**
     * Não incluiu o id como parâmetro no path pois, como não temos um banco de dados de fato,
     * ele estava apenas complicando as coisas.
     * Como os produtos foram definidos como um Set no lugar de List,
     * e na classe {@link Product} foi implementado o equals e hashCode
     * usando o id como critério de comparação, os produtos passam a ser únicos baseados no id.
     * Assim, basta usar o método {@link Set#contains(Object)} para verificar se o produto já existe.
     * Assim, o código é mais simples e, usando um {@link Set} é mais eficiente, pois não precisa
     * fazer uma busca linear como seria necessário se fosse um {@link List}.
     * @param product
     * @return
     */
    @PutMapping
    public Product update(@RequestBody Product product) {
        if(!products.contains(product)) {
            throw newNotFoundException(product.getId());
        }

        products.add(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!products.removeIf(p -> p.getId() == id))
            throw newNotFoundException(id);
    }

    private static ResponseStatusException newNotFoundException(final long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado com id " + id);
    }
}
