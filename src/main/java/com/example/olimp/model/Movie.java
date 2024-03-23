package com.example.olimp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "length")
    private Time length;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "director")
    private Director director;
}
