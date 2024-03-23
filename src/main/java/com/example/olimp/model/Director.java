package com.example.olimp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "director")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "fio")
    private String fio;
}
