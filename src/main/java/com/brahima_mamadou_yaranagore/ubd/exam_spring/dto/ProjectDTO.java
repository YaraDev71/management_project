package com.brahima_mamadou_yaranagore.ubd.exam_spring.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    @NotBlank(message = "Le nom ne dois pas etre vide")
    @Size(min = 2, max = 100, message = "le nom doit comporter au moins 2 caracteres et 50 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le nom du projet ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String name;

    @NotBlank(message = "Le nom ne dois pas etre vide")
    @Size(min = 20, max = 100, message = "le nom doit comporter au moins 20 caracteres et 1000 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " La description  du projet ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String description;
    @NotNull(message = " La date de début est requise ")
    private LocalDate startDate;
    @NotNull(message = " la date de fin est requise")
    @Future(message = " La date de fin dois etre dans le futur ")
    private LocalDate endDate;
    private List<Long> taskIds = new ArrayList<>();
}
