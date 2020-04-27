package com.jjdev.api.controller;

import com.jjdev.api.dto.ProductDto;
import com.jjdev.persistence.dao.ProductDao;
import com.jjdev.persistence.entity.Product;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    public List<Product> findAllProducts() {
        System.out.println("Reading products...");
        return productDao.readAll();
    }

    @POST
    public void createProduct(@Valid ProductDto productDto) {
        System.out.println("Creating product...");
        Product product = Product.builder()
                .name(productDto.getName())
                .value(productDto.getValue())
                .build();
        productDao.create(product);
    }

    @PUT
    @Path("{id}")
    public void updateProduct(@PathParam("id") Long id, @Valid ProductDto productDto) {
        System.out.println("Updating product...");
        Product product = Product.builder()
                .name(productDto.getName())
                .value(productDto.getValue())
                .build();
        productDao.update(product, id);
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") Long id) {
        System.out.println("Deleting product...");
        productDao.delete(id);
    }
}
