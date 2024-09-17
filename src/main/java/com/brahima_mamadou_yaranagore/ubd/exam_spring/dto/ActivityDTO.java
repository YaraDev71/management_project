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
public class ActivityDTO {
    private Long id;
     @NotBlank(message = "Le nom ne dois pas etre vide")
     @Size(min = 2, max = 50, message = "le nom doit comporter au moins 2 caracteres et 50 caracteres au plus ")
     @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le nom de l'activité ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String name ;
     @NotNull(message = " La date de début est requise ")
    private LocalDate startDate;

    @NotNull(message = " la date de fin est requise")
    @Future(message = " La date de fin dois etre dans le futur ")
    private LocalDate endDate;

    @NotNull(message = "Le Status de l'activité est  requires ")
    @Size(min = 2, max = 50, message = "le statut doit comporter au moins 10 caracteres et 100 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le statut de l'activité ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String status;
    private Long taskId ;
    private List<ResourceDTO> ressources = new ArrayList<>();
}
