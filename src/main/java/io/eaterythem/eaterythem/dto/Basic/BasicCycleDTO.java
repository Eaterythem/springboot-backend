package io.eaterythem.eaterythem.dto.Basic;

import java.util.UUID;

import lombok.Data;

@Data
public class BasicCycleDTO {
    private UUID id;

    private String name;

    private boolean isPublic;
}
