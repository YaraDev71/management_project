package com.brahima_mamadou_yaranagore.ubd.exam_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    private String code;
    private String message;
    private Map<String, String> details;
    public ErrorResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
        this.details = null;  // ou vous pouvez définir une valeur par défaut
    }

}