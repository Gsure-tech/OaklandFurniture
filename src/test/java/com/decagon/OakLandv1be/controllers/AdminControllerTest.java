package com.decagon.OakLandv1be.controllers;

import com.decagon.OakLandv1be.dto.UpdateProductDto;
import com.decagon.OakLandv1be.entities.Customer;
import com.decagon.OakLandv1be.entities.Person;
import com.decagon.OakLandv1be.entities.Product;
import com.decagon.OakLandv1be.entities.SubCategory;
import com.decagon.OakLandv1be.enums.Gender;
import com.decagon.OakLandv1be.enums.Role;
import com.decagon.OakLandv1be.exceptions.ProductNotFoundException;
import com.decagon.OakLandv1be.repositries.ProductRepository;
import com.decagon.OakLandv1be.services.ProductService;
import com.decagon.OakLandv1be.utils.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.decagon.OakLandv1be.dto.ProductCustResponseDto;
import com.decagon.OakLandv1be.dto.ProductResponseDto;
import com.decagon.OakLandv1be.repositries.CustomerRepository;
import com.decagon.OakLandv1be.repositries.PersonRepository;
import com.decagon.OakLandv1be.services.serviceImpl.AdminServiceImpl;
import com.decagon.OakLandv1be.services.serviceImpl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AdminServiceImpl adminService;

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    ProductService productService;
    
    @MockBean
    ProductRepository productRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    Person person;
    Product product, updatedProduct;
    UpdateProductDto updateProductDto;

    @BeforeEach
    void setUp() {
         product = Product.builder()
                .name("center table")
                .price(20.0d)
                .imageUrl("")
                .availableQty(10)
                .subCategory(new SubCategory())
                .customer(new Customer())
                .color("blue")
                .description("new products")
                .build();

        product.setId(23L);

        updateProductDto = UpdateProductDto.builder()
                .name("Not a center table")
                .price(20.0d)
                .imageUrl("blah blah")
                .availableQty(20)
                .subCategory(new SubCategory())
                .color("brown")
                .description("old products")
                .build();


        updatedProduct = Product.builder()
                .name("Not a center table")
                .price(20.0d)
                .imageUrl("blah blah")
                .availableQty(20)
                .subCategory(new SubCategory())
                .customer(new Customer())
                .color("brown")
                .description("old products")
                .build();

         person = Person.builder()
                .firstName("Benson")
                .lastName("Malik")
                .email("benson@gmail.com")
                .gender(Gender.MALE)
                .date_of_birth("13-08-1990")
                .phone("9859595959")
                .verificationStatus(true)
                .password(passwordEncoder.encode("password123"))
                .address("No Address")
                .role(Role.ADMIN)
                .build();
         person.setId(1L);
    }

    @Test
    void updateProduct() {
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product, productRepository.save(product));

        when(productRepository.findById(any()))
                .thenReturn(Optional.of(product));

        ApiResponse<Product> expectedApiResponse =
         new ApiResponse<>("product updated", true, updatedProduct);

        ApiResponse<Product> apiResponse1 =
                productService.updateProduct(32l, updateProductDto);
        assertEquals(apiResponse1.toString(), expectedApiResponse.toString());

    }

    @Test
    void shouldThrowProductNotFoundException() {
        when(productRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(23L, updateProductDto));


    @Test
    void viewASingleProduct() {
        try {
            SubCategory subCategory = new SubCategory();
            Product product = new Product();
            product.setId(2L);
            product.setName("Tall dinning chair");
            product.setImageUrl("hgdhg");
            product.setColor("green");
            product.setPrice(2000.00);
            product.setDescription("strong black");
            ProductResponseDto productResponseDto = ProductResponseDto.builder().name("Tall dinning chair").price(2000.00).imageUrl("hgdhg").availableQty(3)
                    .subCategory(subCategory).color("green").description("strong black").build();
            when(adminService.fetchASingleProduct(anyLong())).thenReturn(productResponseDto);
            mockMvc.perform(get("/api/v1/admin/products/{product_id}", 2L)
                            .contentType("application/json"))
                    .andExpect(status().isOk());
        } catch (Exception ce){
            ce.printStackTrace();
        }
    }
}