package com.demo.hotelreservation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "amenities")
@Getter
@Setter
@NoArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    @Column(nullable = false, unique = true, length = 100)
    private String amenityName;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "amenities", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();
}
