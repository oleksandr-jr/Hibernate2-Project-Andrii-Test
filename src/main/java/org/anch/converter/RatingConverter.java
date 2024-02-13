package org.anch.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.anch.entity.Rating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {


    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getLabel();
    }

    @Override
    public Rating convertToEntityAttribute(String label) {
        if (label == null) {
            return null;
        }
        return Rating.valueOfLabel(label);
    }
}
