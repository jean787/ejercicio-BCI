package com.jherrell.msusers.model.db;

import static jakarta.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_created")
    private String created;
    @Column(name = "user_modified")
    private String modified;
    @Column(name = "user_last_login")
    private String lastLogin;
    @Column(name = "user_token")
    private String token;
    @Column(name = "user_is_active")
    private Boolean isActive;
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones;

}
