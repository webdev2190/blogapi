package com.blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    //@ManyToMany(fetch = FetchType.EAGER) is a JPA annotation used to specify the fetching strategy for a many-to-many relationship between two entities.
    // In this case, EAGER means that when an entity is loaded, its associated many-to-many entities will also be loaded immediately.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            //joinColumns = @JoinColumn(name = "user_id") means that the "user_id" column in the table representing the owning side of the relationship will be used to join the entities.
            joinColumns = @JoinColumn(name = "user_id"),

            //inverseJoinColumns = @JoinColumn(name = "role_id") means that the "role_id" column in the table representing the inverse side of the relationship will be used to join the entities.
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();//Using set here so that Role objects will not duplicate

    // Constructors, getters, setters, and other methods



    }

