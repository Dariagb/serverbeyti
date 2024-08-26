package ru.daria.serverbeyti.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String post;
    @Column
    private Integer age;
    @Column
    private String phone;
}
