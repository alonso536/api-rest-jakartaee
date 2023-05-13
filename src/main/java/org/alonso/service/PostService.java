package org.alonso.service;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.alonso.entity.Post;
import org.alonso.repository.RepositoryDao;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"ADMIN", "USER"})
public class PostService implements Service<Post> {
    @Inject
    private RepositoryDao<Post> repository;

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public List<Post> index() {
        return repository.index();
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void create(Post post) {
        this.repository.create(post);
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public Optional<Post> show(Integer id) {
        return Optional.ofNullable(this.repository.show(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void edit(Post post) {
        this.repository.edit(post);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void destroy(Integer id) {
        this.repository.destroy(id);
    }
}
