package org.alonso.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.alonso.entity.Comment;
import org.alonso.entity.Post;

import java.util.List;

@RequestScoped
public class CommentDao implements RepositoryDao<Comment> {
    @Inject
    EntityManager entityManager;
    @Override
    public List<Comment> index() {
        return entityManager.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    public void create(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public Comment show(Integer id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public void edit(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    public void destroy(Integer id) {
        entityManager.remove(this.show(id));
    }
}
