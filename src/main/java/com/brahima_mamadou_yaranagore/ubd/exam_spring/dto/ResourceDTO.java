package com.brahima_mamadou_yaranagore.ubd.exam_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceDTO {
    private Long id;
    @NotBlank(message = "Le nom de la ressource  ne dois pas etre vide")
    @Size(min = 2, max = 50, message = "le nom doit comporter au moins 2 caracteres et 50 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le nom de la ressource ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String name;

    @NotBlank(message = "Le Type de la ressource ne dois pas etre vide")
    @Size(min = 2, max = 50, message = "le nom doit comporter au moins 2 caracteres et 50 caracteres au plus ")
    @Pattern(regexp = "^[a-zA-Z0-9@#$_\\-\\/ ]+$", message = " Le type de la ressource ne doit contenir que des lettres, des nombres,des espaces, et des caractèes spéciaux: @, #, $, _, -, /\" " )
    private String type;

    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = " Veillez saisir une URL valide ")
    private String url;

    @NotNull(message = " La date de creation est requise ")
    private LocalDate create_date;
    private Long activityId;
}
