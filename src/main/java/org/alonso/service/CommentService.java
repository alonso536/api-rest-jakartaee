package org.alonso.service;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.alonso.entity.Comment;
import org.alonso.repository.RepositoryDao;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"ADMIN", "USER"})
public class CommentService implements Service<Comment> {
    @Inject
    RepositoryDao<Comment> repository;
    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public List<Comment> index() {
        return repository.index();
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void create(Comment comment) {
        repository.create(comment);
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public Optional<Comment> show(Integer id) {
        return Optional.ofNullable(repository.show(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void edit(Comment comment) {
        repository.edit(comment);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void destroy(Integer id) {
        repository.destroy(id);
    }
}
