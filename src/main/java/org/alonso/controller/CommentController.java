package org.alonso.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.alonso.entity.Comment;
import org.alonso.entity.Post;
import org.alonso.service.Service;

import java.util.Optional;

@RequestScoped
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {
    @Inject
    Service<Comment> service;

    @GET
    public Response index() {
        return Response.ok(service.index()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Comment comment) {
        try {
            service.create(comment);
            return Response.ok(comment).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response show(@PathParam("id") Integer id) {
        Optional<Comment> optionalPost = service.show(id);
        if(optionalPost.isPresent()) {
            return Response.ok(optionalPost.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Post post) {
        Optional<Comment> optionalComment = service.show(id);
        if(optionalComment.isPresent()) {
            Comment newComment = optionalComment.get();
            newComment.setId(id);
            newComment.setBody(post.getBody());

            try {
                service.edit(newComment);
                return Response.ok(newComment).build();
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
        Optional<Comment> optionalComment = service.show(id);
        if(optionalComment.isPresent()) {
            service.destroy(id);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
