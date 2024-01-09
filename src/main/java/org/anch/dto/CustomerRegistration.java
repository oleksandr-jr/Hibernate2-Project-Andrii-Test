package org.anch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(setterPrefix = "with")
public class CustomerRegistration {

    private String firstName;
    private String lastName;
    private String email;
    public Short addressId;
    public Byte storeId;
    private Boolean isActive;

}
