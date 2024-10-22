package ru.daria.serverbeyti.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerId;

    @Column(name = "name")
    String name;

    @Column(name="adress")
    String adress;

    @Column(name="phone")
    String phoneNumber;

    @Column(name="country")
    String country;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;
//    @OneToMany(targetEntity = Product.class)
//    @JoinColumn(name = "mp",referencedColumnName = "manufacturerId")
//    private List<Product> products;
}
