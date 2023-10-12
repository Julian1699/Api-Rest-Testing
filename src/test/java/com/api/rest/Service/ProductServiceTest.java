package com.api.rest.Service;

import com.api.rest.Domain.Product;
import com.api.rest.Repository.ProductRepository;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setup(){
        product = Product.builder()
                .id(1L)
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
        //Given
        given(productRepository.save(product)).willReturn(product);

        //When
        Product productSaved = productService.saveProduct(product);

        //Then
        assertThat(productSaved).isNotNull();
    }

    @DisplayName("Test para listar todos los productos")
    @Test
    void testGetProducts() {
        //Given
        Product product1 = Product.builder()
                .id(1L)
                .name("Julen")
                .reference("Aztec")
                .price(10)
                .weight(10)
                .category("Warrior")
                .stock(10)
                .build();
        given(productRepository.findAll()).willReturn(List.of(product,product1));
        //When
        List<Product> products = productService.getProducts();
        //Then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
    }

    @DisplayName("Test para actualizar un producto")
    @Test
    void testUpdateProduct() {
        // Given
        Product updatedProduct = Product.builder()
                .id(1L)
                .name("Corvette")
                .reference("Chevrolet")
                .price(15)
                .weight(12)
                .category("Transport")
                .stock(8)
                .build();

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        given(productRepository.save(product)).willReturn(product);

        // When
        Product result = productService.updateProduct(product.getId(), updatedProduct);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(product.getId());
        assertThat(result.getName()).isEqualTo(updatedProduct.getName());
        assertThat(result.getReference()).isEqualTo(updatedProduct.getReference());
        assertThat(result.getPrice()).isEqualTo(updatedProduct.getPrice());
        assertThat(result.getWeight()).isEqualTo(updatedProduct.getWeight());
        assertThat(result.getCategory()).isEqualTo(updatedProduct.getCategory());
        assertThat(result.getStock()).isEqualTo(updatedProduct.getStock());
    }


    @DisplayName("Test para eliminar un producto existente")
    @Test
    void testDeleteExistingProduct() {
        //Given
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        //When
        productService.deleteProduct(product.getId());

        //Then
        verify(productRepository,times(1)).delete(product);
    }

    @DisplayName("Test para obtener un producto por ID")
    @Test
    void testFindById() {
        // Given
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        // When
        Product retrievedProduct = productService.findById(product.getId());

        // Then
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getId()).isEqualTo(1L);
        assertThat(retrievedProduct.getName()).isEqualTo("Camaro");
        assertThat(retrievedProduct.getReference()).isEqualTo("Chevrolet");
        assertThat(retrievedProduct.getPrice()).isEqualTo(10);
        assertThat(retrievedProduct.getWeight()).isEqualTo(10);
        assertThat(retrievedProduct.getCategory()).isEqualTo("Transport");
        assertThat(retrievedProduct.getStock()).isEqualTo(10);
    }

    @DisplayName("Test para obtener un producto por caracteres")
    @Test
    void testFindProductBySearch() {
        // Given
        String searchTerm = "Camaro";
        List<Product> products = new ArrayList<>();
        products.add(product);
        given(productRepository.findAllBySearch(searchTerm)).willReturn(products);

        // When
        Product foundProduct = productService.findAllChars(searchTerm);

        // Then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(1L);
        assertThat(foundProduct.getName()).isEqualTo("Camaro");
        assertThat(foundProduct.getReference()).isEqualTo("Chevrolet");
        assertThat(foundProduct.getPrice()).isEqualTo(10);
        assertThat(foundProduct.getWeight()).isEqualTo(10);
        assertThat(foundProduct.getCategory()).isEqualTo("Transport");
        assertThat(foundProduct.getStock()).isEqualTo(10);
    }

}