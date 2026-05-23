package com.s.commerce.domain.user.entity;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.user.enums.UserRole;

import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import com.s.commerce.domain.user.valueObject.UserId;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
public class User extends Auditable {

    @Getter
    @EmbeddedId
    private UserId id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private Email email;
    @Column(nullable = false)
    private HashedPassword password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    protected User() {
    }

    public User(String name, Email email, HashedPassword password, UserRole role) {
        this.id = UserId.newId();
        this.changeName(name);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    public void changeName(String name) {
        validateName(name);

        this.name = name;
    }


    public void changeEmail(Email email) {
        this.email = email;
    }


}