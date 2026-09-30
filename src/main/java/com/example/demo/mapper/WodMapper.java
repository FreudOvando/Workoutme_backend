package com.example.demo.mapper;

import com.example.demo.dto.WodRequest;
import com.example.demo.dto.WodResponse;
import com.example.demo.model.Wod;
import org.springframework.stereotype.Component;

@Component
public class WodMapper {

    public Wod toEntity(WodRequest request) {
        Wod wod = new Wod();
        applyRequest(wod, request);
        return wod;
    }

    // Se usa al actualizar: modifica la entidad ya existente
    public void updateEntity(Wod wod, WodRequest request) {
        applyRequest(wod, request);
    }

    public WodResponse toResponse(Wod wod) {
        return new WodResponse(
                wod.getId(),
                wod.getName(),
                wod.getPublicationDate(),
                wod.getCoachName(),
                wod.getType(),
                wod.getDescription(),
                wod.getCreatedAt(),
                wod.getUpdatedAt()
        );
    }

    private void applyRequest(Wod wod, WodRequest request) {
        wod.setName(normalizeName(request.name()));
        wod.setPublicationDate(request.publicationDate());
        wod.setCoachName(request.coachName().trim());
        wod.setType(request.type());
        wod.setDescription(request.description().trim());
    }

    // El nombre es opcional: si llega vacío o con espacios, se guarda como null
    private String normalizeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return name.trim();
    }
}