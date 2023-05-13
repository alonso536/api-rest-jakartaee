package org.alonso.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.alonso.entity.Post;
import org.alonso.service.Service;

import java.util.Optional;

@RequestScoped
@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostController {
    @Inject
    Service<Post> service;

    @GET
    public Response index() {
        return Response.ok(service.index()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Post post) {
        try {
            service.create(post);
            return Response.ok(post).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") Integer id) {
        Optional<Post> optionalPost = service.show(id);
        if(optionalPost.isPresent()) {
            return Response.ok(optionalPost.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Post post) {
        Optional<Post> optionalPost = service.show(id);
        if(optionalPost.isPresent()) {
            Post newPost = optionalPost.get();
            newPost.setId(id);
            newPost.setTitle(post.getTitle());
            newPost.setBody(post.getBody());

            try {
                service.edit(newPost);
                return Response.ok(newPost).build();
            } catch(Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response destroy(@PathParam("id") Integer id) {
        Optional<Post> optionalPost = service.show(id);
        if(optionalPost.isPresent()) {
            service.destroy(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
