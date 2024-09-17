package com.brahima_mamadou_yaranagore.ubd.exam_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ressources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;


    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String url;

    @Column(name = "create_date", nullable = false, columnDefinition = "Date Default CURRENT_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate create_date;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

}
