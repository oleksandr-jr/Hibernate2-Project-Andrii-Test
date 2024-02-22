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
    private Integer languageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private Rating rating;
    private String specialFeatures;
    private Set<Integer> categoriesId;
    private Set<Integer> actorsId;

}
