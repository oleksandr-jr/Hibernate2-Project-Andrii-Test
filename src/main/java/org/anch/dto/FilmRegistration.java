package org.anch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anch.entity.Rating;

import java.time.Year;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class FilmRegistration {

    private String title;
    private String description;
    private Year releaseYear;
    private Byte languageId;
    private Byte rentalDuration;
    private Double rentalRate;
    private Short length;
    private Double replacementCost;
    private Rating rating;
    private String specialFeatures;
    private Set<Byte> categoriesId;
    private Set<Short> actorsId;

}
