package com.pluralsight.bookstore.rest;


import com.pluralsight.bookstore.model.Product;
import com.pluralsight.bookstore.repository.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/products")
public class ProductEndpoint {

    @Inject
    private ProductRepository productRepository;

    @GET
    @Produces(APPLICATION_JSON)
    public Response getProducts() {
        List<Product> products = productRepository.findAll();
        if (products.size() == 0)
            return Response.noContent().build();
        return Response.ok(products).build();
    }



}
