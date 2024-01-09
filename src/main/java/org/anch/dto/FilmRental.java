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

    private short filmId;
    private short customerId;
    private byte staffId;
    private double amount;

}
