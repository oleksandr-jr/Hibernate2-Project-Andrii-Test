package org.anch.service;

import org.anch.dto.CustomerRegistration;
import org.anch.dto.FilmRegistration;
import org.anch.dto.FilmRental;

public interface ApplicationService {
    void saveCustomer(CustomerRegistration customerRegistration);

    void returnRentedFilmToStore(Integer rentalId);

    Boolean rentNewFilm(FilmRental filmRental);

    Integer addNewFilmToInventories(FilmRegistration filmRegistration);

}
