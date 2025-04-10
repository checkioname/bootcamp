package com.example.esportistas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public record Sport() implements Serializable {
    private static String name;
}
