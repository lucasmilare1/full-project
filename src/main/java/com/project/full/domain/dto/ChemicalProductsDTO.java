package com.project.full.domain.dto;

import com.project.full.util.config.Danger;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChemicalProductsDTO {

    private Long id;

    private String ProductName;

    private Danger danger;

    private String classification;
}
