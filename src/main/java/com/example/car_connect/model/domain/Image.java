package com.example.car_connect.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String path;
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car carDetail;

    @OneToOne(mappedBy = "fonImage")
    private Car car;

    @OneToOne(mappedBy = "image")
    private User user;
}
