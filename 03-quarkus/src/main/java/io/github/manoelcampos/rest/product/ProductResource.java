package io.github.manoelcampos.rest.product;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/product")
// JSON é o padrão, mas precisa adicionar quarkus-rest-jsonb
// @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    /**
     * Armazena uma lista de produtos de exemplo na própria classe,
     * apenas como demonstração (já que não estamos usando banco de dados para este resource).
     */
    private static final List<Product> products =
            new ArrayList<>(List.of(
                Product.of(1, "Sample Product 1", 10.99),
                Product.of(2, "Sample Product 2", 15.00)
            ));

    @GET
    public List<Product> getProducts() {
        return products;
    }

    @POST
    public Product create(Product product) {
        final var newProduct = Product.of(products.size()+1, product);
        products.add(newProduct);
        System.out.println("Product created: " + newProduct);
        return newProduct;
    }
}
