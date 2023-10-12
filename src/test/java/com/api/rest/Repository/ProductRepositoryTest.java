package com.api.rest.Repository;

import com.api.rest.Domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setup(){
        product = Product.builder()
                .name("Camaro")
                .reference("Chevrolet")
                .price(10)
                .weight(10)
                .category("Transport")
                .stock(10)
                .build();
    }

    @DisplayName("Test para guardar un producto")
    @Test
    void testSaveProduct() {
        Product product = Product.builder()
                .name("Luigui")
                .reference("Italy")
                .price(10)
                .weight(10)
                .category("Person")
                .stock(10)
                .build();
        Product productSave = productRepository.save(product);

        assertThat(productSave).isNotNull();
    }

    @DisplayName("Test para listar todos los productos")
    @Test
    void testGetProducts() {
        //GIVEN
        Product product1 = Product.builder()
                .name("Bod Marley")
                .reference("Jamaica")
                .price(10)
                .weight(10)
                .category("Person")
                .stock(10)
                .build();
        Product productSave = productRepository.save(product1);

        assertThat(productSave).isNotNull();
        //WHEN
        List<Product> listarProductos = productRepository.findAll();
        //THEN
        assertThat(listarProductos).isNotNull();
        assertThat(listarProductos.size()).isEqualTo(1);
    }

    @DisplayName("Test para actualizar un producto")
    @Test
    void testUpdateProduct() {
        productRepository.save(product);
        //WHEN
        Product productSave = productRepository.findById(product.getId()).get();
        productSave.setName("Paco");
        productSave.setReference("Naco");
        productSave.setPrice(100);
        productSave.setWeight(30);
        productSave.setCategory("Mexico");
        productSave.setStock(100);

        Product productUpdate = productRepository.save(productSave);

        //THEN
        assertThat(productUpdate.getName()).isEqualTo("Paco");
        assertThat(productUpdate.getReference()).isEqualTo("Naco");
        assertThat(productUpdate.getPrice()).isEqualTo(100);
        assertThat(productUpdate.getWeight()).isEqualTo(30);
        assertThat(productUpdate.getCategory()).isEqualTo("Mexico");
        assertThat(productUpdate.getStock()).isEqualTo(100);

    }

    @DisplayName("Test para eliminar un producto")
    @Test
    void testDeleteProduct() {
        // Guarda el producto en la base de datos
        product = productRepository.save(product);

        // Verifica que el producto se haya guardado correctamente
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        assertTrue(optionalProduct.isPresent(), "El producto debería existir en la base de datos antes de la eliminación");

        // Llama al método deleteById() del repositorio para eliminar el producto por su ID
        productRepository.deleteById(product.getId());

        // Intenta encontrar el producto después de eliminarlo
        Optional<Product> deletedProduct = productRepository.findById(product.getId());

        // Verifica que el producto no se pueda encontrar después de la eliminación
        assertFalse(deletedProduct.isPresent(), "El producto debería ser eliminado de la base de datos");
    }

    @DisplayName("Test para obtener un producto por ID")
    @Test
    void testFindById() {

        productRepository.save(product);

        Optional<Product> productBD = productRepository.findById(product.getId());

        assertThat(productBD).isNotNull();
    }

    @DisplayName("Test para obtener un producto por caracteres")
    @Test
    void testFindProductBySearch() {
        // Guarda un producto en la base de datos
        productRepository.save(product);

        // Llama a findAllChars con una cadena de búsqueda válida
        List<Product> searchResults = productRepository.findAllBySearch("Camaro");

        // Verifica que haya al menos un resultado
        assertFalse(searchResults.isEmpty(), "No se encontraron productos con la búsqueda 'Jabon'");

        // Verifica que el primer producto de la lista no sea nulo
        Product foundProduct = searchResults.get(0);
        assertThat(foundProduct).isNotNull();
    }

}