package com.quixindodev.api_springboot.controllers;

import com.quixindodev.api_springboot.dtos.ProductRecordDto;
import com.quixindodev.api_springboot.models.ProductModel;
import com.quixindodev.api_springboot.repositories.IProductRepository;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    IProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productList = productRepository.findAll();
        if (!productList.isEmpty()){
            for (var product: productList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> produt0 = productRepository.findById(id);
        if (produt0.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");

        produt0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products"));
        return ResponseEntity.status(HttpStatus.OK).body(produt0.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
