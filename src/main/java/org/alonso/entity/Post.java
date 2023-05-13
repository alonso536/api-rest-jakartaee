package org.alonso.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String body;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<Comment> comments;
    @Getter @Setter @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Getter @Setter @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Post() {
        this.comments = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
