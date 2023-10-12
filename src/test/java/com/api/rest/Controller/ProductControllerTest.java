package com.api.rest.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.api.rest.Domain.Product;
import com.api.rest.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Test para obtener todos los productos")
    @Test
    void testGetAllProducts() throws Exception {
        // Given
        Product product1 = Product.builder()
                .id(1L)
                .name("Camaro")
                .reference("Chevrolet")
                .price(10)
                .weight(10)
                .category("Transport")
                .stock(10)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("Mustang")
                .reference("Ford")
                .price(12)
                .weight(12)
                .category("Transport")
                .stock(8)
                .build();

        List<Product> products = Arrays.asList(product1, product2);

        given(productService.getProducts()).willReturn(products);

        // When
        ResultActions response = mockMvc.perform(get("/api/v1/product/all")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Camaro")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Mustang")));

        verify(productService, times(1)).getProducts();
    }

    @DisplayName("Test para guardar un producto por medio del Controller")
    @Test
    void testPostProduct() throws Exception {
        // Given
        Product product = Product.builder()
                .id(1L)
                .name("Camaro")
                .reference("Chevrolet")
                .price(10)
                .weight(10)
                .category("Transport")
                .stock(10)
                .build();

        given(productService.saveProduct(any(Product.class))).willReturn(product);

        // When
        ResultActions response = mockMvc.perform(post("/api/v1/product/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @DisplayName("Test para actualizar un producto existente")
    @Test
    void testPutProduct() throws Exception {
        // Given
        Long productId = 1L;
        Product product = Product.builder()
                .id(productId)
                .name("Updated Camaro")
                .reference("Updated Chevrolet")
                .price(20)
                .weight(15)
                .category("Transport")
                .stock(5)
                .build();

        given(productService.updateProduct(eq(productId), any(Product.class))).willReturn(product);

        // When
        ResultActions response = mockMvc.perform(put("/api/v1/product/put/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        verify(productService, times(1)).updateProduct(eq(productId), any(Product.class));
    }

    @DisplayName("Test para eliminar un producto existente")
    @Test
    void testDeleteProduct() throws Exception {
        // Given
        Long productId = 1L;

        // When
        ResultActions response = mockMvc.perform(delete("/api/v1/product/delete/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andDo(print())
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(eq(productId));
    }

    @DisplayName("Test para obtener un producto existente por ID")
    @Test
    void testGetProductById() throws Exception {
        // Given
        Long productId = 1L;
        Product product = Product.builder()
                .id(productId)
                .name("Camaro")
                .reference("Chevrolet")
                .price(10)
                .weight(10)
                .category("Transport")
                .stock(10)
                .build();

        given(productService.findById(eq(productId))).willReturn(product);

        // When
        ResultActions response = mockMvc.perform(get("/api/v1/product/id/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Camaro")));

        verify(productService, times(1)).findById(eq(productId));
    }

    @DisplayName("Test para obtener productos por búsqueda exitosa")
    @Test
    void testGetProductsBySearch() throws Exception {
        // Given
        String searchTerm = "Camaro";
        Product product = Product.builder()
                .id(1L)
                .name("Camaro")
                .reference("Chevrolet")
                .price(10)
                .weight(10)
                .category("Transport")
                .stock(10)
                .build();

        given(productService.findAllChars(eq(searchTerm))).willReturn(product);

        // When
        ResultActions response = mockMvc.perform(get("/api/v1/product/search/{search}", searchTerm)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Camaro")));

        verify(productService, times(1)).findAllChars(eq(searchTerm));
    }
}