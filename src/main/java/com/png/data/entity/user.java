package com.png.data.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Arindam on 5/28/2017.
 */

@Entity
@Table(name="user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name="email")
    @Email(message = "Please provide a valid email")
    @NotEmpty(message = "Please provide an email")


    private String email;

    @Column (name="password")
    @Length (min = 5,message = "Password must have 5 characters")
    @NotEmpty (message = "Please provide a password")
    @Transient
    private String password;


}
