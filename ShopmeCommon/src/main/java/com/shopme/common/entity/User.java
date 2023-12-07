package com.shopme.common.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;


    @Column(length = 64)
    private String photos;


    private boolean enabled;

    /*
    @ManyToMany: Indicates a many-to-many relationship between entities. In this case, a User can have multiple Roles, and a Role can be associated with multiple Users.

@JoinTable: This annotation specifies the details of the join table that will be used to manage the many-to-many relationship between the User and Role entities.

name = "user_roles": Specifies the name of the join table. In this case, the table named user_roles will be created in the database to manage the relationship between users and roles.

joinColumns: Represents the column in the user_roles table that corresponds to the User entity.

@JoinColumn(name="user_id"): Indicates that the user_id column in the user_roles table will contain references to the User entity.
inverseJoinColumns: Represents the column in the user_roles table that corresponds to the Role entity.

@JoinColumn(name = "role_id"): Indicates that the role_id column in the user_roles table will contain references to the Role entity.
private Set<Role> roles = new HashSet<>();: This line declares a Set of Role objects named roles in the User class. This set represents the collection of roles associated with a user. It's initialized as an empty HashSet.

This setup allows you to link a User entity to multiple Role entities via a join table called user_roles in the database, facilitating a many-to-many relationship between users and roles in your application's data model.
     */
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
