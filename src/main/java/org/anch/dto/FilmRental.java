package org.anch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class FilmRental {

    private Integer filmId;
    private Integer customerId;
    private Integer staffId;
    private Double amount;

}
