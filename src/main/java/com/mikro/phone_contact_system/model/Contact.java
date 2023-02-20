package com.mikro.phone_contact_system.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * @author Brume
 **/
@Entity
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;


}
