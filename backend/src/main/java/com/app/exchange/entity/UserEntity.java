package com.app.exchange.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "isTeacher")
    @JsonProperty("isTeacher")
    private Boolean isTeacher;
    public UserEntity() {}
    public UserEntity(String name, String mail, String password, Boolean isTeacher) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getTeacher() {
        return isTeacher;
    }

    public void setTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
}
