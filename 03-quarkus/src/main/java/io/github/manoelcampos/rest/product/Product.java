package io.github.manoelcampos.rest.product;

/// Representa um produto.
/// Como não estamos usando JPA, um record funciona perfeitamente.
/// [JPA não tem suporte total a records](https://thorben-janssen.com/java-records-hibernate-jpa/#records-cant-be-entities),
/// pois ela precisa alterar o estado de objetos em operações como insert e update.
/// @author Manoel Campos
public record Product(Long id, String description, double price) {

    /**
     * {@return um novo produto sem id}
     * Não dá pra criar um construtor sobrecarregado
     * pois a extensão rest-json-b não consegue escolher
     * o construtor correto para deserializar o JSON recebido
     * em uma requisição para um objeto Java,
     * dando erro em runtime.
     */
    public static Product of(long id, String description, double price) {
        return new Product(id, description, price);
    }

    /**
     * Instancia um novo produto, a partir de outro informado,
     * mas com um novo ID.
     * @param id novo ID para o produto a ser instanciado
     * @param p produto para copiar os dados para a nova instância
     * @return a nova instância de Produto
     */
    public static Product of(long id, Product p) {
        return new Product(id, p.description, p.price);
    }


}
