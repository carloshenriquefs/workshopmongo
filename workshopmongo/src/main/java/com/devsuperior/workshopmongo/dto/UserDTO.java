package com.devsuperior.workshopmongo.dto;

import com.devsuperior.workshopmongo.models.entities.User;

public class UserDTO {

    private String id;
    private String name;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}
