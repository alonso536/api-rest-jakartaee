package org.alonso.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.alonso.entity.Post;

import java.util.List;

@RequestScoped
public class PostDao implements RepositoryDao<Post> {
    @Inject
    private EntityManager entityManager;

    @Override
    public List<Post> index() {
        return entityManager.createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    public void create(Post post) {
        entityManager.persist(post);
    }

    @Override
    public Post show(Integer id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void edit(Post post) {
        entityManager.merge(post);
    }

    @Override
    public void destroy(Integer id) {
        entityManager.remove(this.show(id));
    }
}
