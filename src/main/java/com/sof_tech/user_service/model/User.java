package com.sof_tech.user_service.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "micro_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    // SerialVersionUID ensures that different versions of the class are compatible with serialization
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")  // Added length for the userId
    private String userId;

    @Column(name = "NAME", length = 20)  // Added length for the name
    private String name;

    @Column(name = "EMAIL", length = 20)  // Added length for the email
    private String email;

    @Column(name = "ABOUT", length = 255)  // Added length for the about
    private String about;

}
