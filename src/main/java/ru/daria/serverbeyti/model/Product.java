package ru.daria.serverbeyti.model;

import ch.qos.logback.classic.spi.ConfiguratorRank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "shade_number")
    private int shade_number;

    @Column(name = "volume")
    private int volume;

    @Column(name = "country")
    private String country;
}
