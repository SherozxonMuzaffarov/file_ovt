package com.uzvagontamir.File.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false,length = 40)
    private String username;
    @Column(unique = true,nullable = false,length = 10)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role  role;
    private String korxona;

    public User(String username, String password,  String korxona, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.korxona = korxona;
    }

    public User() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getKorxona() {
        return korxona;
    }

    public void setKorxona(String korxona) {
        this.korxona = korxona;
    }
}
