package com.brahima_mamadou_yaranagore.ubd.exam_spring.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;

    @NotBlank(message = "Le nom ne dois pas etre vide")
    @Size(min = 2, max = 50, message = "le nom doit comporter au moins 2 caracteres et 50 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le nom de la tache ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String name;

    @NotNull(message = " La date de début est requise ")
    private LocalDate startDate;

    @NotNull(message = " la date de fin est requise")
    @Future(message = " La date de fin dois etre dans le futur ")
    private LocalDate endDate;

    @NotNull(message = " la priorite de la tache est requise ")
    @Size(min = 10, max = 100, message = "la priorité de la tache doit comporter au moins 10 caracteres et 100 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " La priorité de la tache ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String priority ;
     Long projectId;
    private List<ActivityDTO> activites = new ArrayList<>();
}
