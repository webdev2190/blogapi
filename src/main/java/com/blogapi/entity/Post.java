package com.blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data //this Annotation give automatically gives gitter and setters
@AllArgsConstructor //If you want constructor then you can write like this is equivalent to constructor.class
@NoArgsConstructor // Whenever there is @AllArgsConstructor annotation then you need to Mention @NoArgsConstructor this is the rule. otherwise it's gives error.
//Default constructor does nothing exist there.
@Entity //this on is for Hibernate
@Table(name = "Posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}) //Now this is become a Unique title for the table.

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();




}
