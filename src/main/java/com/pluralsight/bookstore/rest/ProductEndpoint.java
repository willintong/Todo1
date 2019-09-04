package com.pluralsight.bookstore.rest;


import com.pluralsight.bookstore.model.Product;
import com.pluralsight.bookstore.repository.ProductRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
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
/*
    @POST
    @Consumes(APPLICATION_JSON)
    public  Response createProduct(Product product, @Context UriInfo uriInfo){
        product = ProductRepository.create(product);
        URI createdURI = uriInfo.getBaseUriBuilder().path(product.getId().toString()).build();
        return Response.created(createdURI).build();
    }
*/
    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteProduct(@PathParam("id") @Min(1) long id){
        productRepository.delete(id);
        return Response.noContent().build();
    }


}
