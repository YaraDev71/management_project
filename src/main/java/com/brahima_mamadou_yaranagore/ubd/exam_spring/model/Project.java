package com.brahima_mamadou_yaranagore.ubd.exam_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projet")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;


    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

}
