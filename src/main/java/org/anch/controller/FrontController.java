package org.anch.controller;

import lombok.AllArgsConstructor;
import org.anch.dto.CustomerRegistration;
import org.anch.dto.FilmRegistration;
import org.anch.dto.FilmRental;
import org.anch.entity.Actor;
import org.anch.entity.Rating;
import org.anch.service.ApplicationService;
import org.anch.view.ViewProvider;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class FrontController {

    private static final String MENU = "\n\t ============ Please, choose what do you want to do ============\n"
            + "1 -> Create new Customer \n"
            + "2 -> Return rented Film to Store \n"
            + "3 -> Rent new Film \n"
            + "4 -> Add new Film to Inventories  \n"
            + "0 -> To exit from the program \n";
    private static final String ACTOR_ADDING = "\n\t Please, add Actor id to the Film, or press \"0\" if there is not any Actor left \n";
    private static final String CATEGORY_ADDING = "\n\t Please, add Category id to the Film, or press \"0\" if there is not any Category left \n";
    private static final String WRONG_CHOICE_MESSAGE = "Please, make right choice from the list or enter \"0\" to exit from the program";
    private final ApplicationService applicationService;
    private final ViewProvider viewProvider;

    public void run() {
        boolean isWork = true;
        while (isWork) {
            viewProvider.printMessage(MENU);
            int choice = viewProvider.readInt();
            switch (choice) {
                case 0 -> isWork = false;
                case 1 -> createNewCustomer();
                case 2 -> returnRentedFilmToStore();
                case 3 -> rentNewFilm();
                case 4 -> addNewFilmToInventories();
                default -> viewProvider.printMessage(WRONG_CHOICE_MESSAGE);
            }
        }
    }

    private void createNewCustomer() {
        viewProvider.printMessage("Enter Customer first name: ");
        String firstName = viewProvider.readString();
        viewProvider.printMessage("Enter Customer last name: ");
        String lastName = viewProvider.readString();
        viewProvider.printMessage("Enter Customer email: ");
        String email = viewProvider.readString();
        viewProvider.printMessage("Enter Customer Address Id: ");
        short addressId = viewProvider.readShort();
        viewProvider.printMessage("Enter Store Id: ");
        byte storeId = viewProvider.readByte();
        viewProvider.printMessage("Enter Customer Active Status (true or false): ");
        boolean isActive = viewProvider.readBoolean();
        CustomerRegistration customerRegistration = CustomerRegistration.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withAddressId(addressId)
                .withStoreId(storeId)
                .withIsActive(isActive)
                .build();
        applicationService.saveCustomer(customerRegistration);
    }

    private void returnRentedFilmToStore() {
        viewProvider.printMessage("Enter Rental Id: ");
        int rentalId = viewProvider.readInt();
        applicationService.returnRentedFilmToStore(rentalId);
    }

    private void rentNewFilm() {
        viewProvider.printMessage("Enter Film Id: ");
        short filmId = viewProvider.readShort();
        viewProvider.printMessage("Enter Customer Id: ");
        short customerId = viewProvider.readShort();
        viewProvider.printMessage("Enter Staff Id: ");
        byte staffId = viewProvider.readByte();
        viewProvider.printMessage("Enter cost amount: ");
        double amount = viewProvider.readDouble();
        FilmRental filmRental = FilmRental.builder()
                .withFilmId(filmId)
                .withCustomerId(customerId)
                .withStaffId(staffId)
                .withAmount(amount)
                .build();
        Boolean isFilmAvailable = applicationService.rentNewFilm(filmRental);
        if (isFilmAvailable) {
            System.out.println("You have rented the film...");
        } else {
            System.out.println("Film is not available right now...");
        }
    }

    private void addNewFilmToInventories() {
        viewProvider.printMessage("New Film registration");
        viewProvider.printMessage("Enter Film Title: ");
        String title = viewProvider.readString();
        viewProvider.printMessage("Enter Film Description: ");
        String description = viewProvider.readString();
        viewProvider.printMessage("Enter Release Year in the format \"1999\" : ");
        Year releaseYear = Year.of(viewProvider.readInt());
        viewProvider.printMessage("Enter Language Id: ");
        byte languageId = viewProvider.readByte();
        viewProvider.printMessage("Enter Rental Duration : ");
        byte rentalDuration = viewProvider.readByte();
        viewProvider.printMessage("Enter rentalRate: ");
        double rentalRate = viewProvider.readDouble();
        viewProvider.printMessage("Enter Film Length: ");
        short length = viewProvider.readShort();
        viewProvider.printMessage("Enter replacement cost: ");
        double replacementCost = viewProvider.readDouble();
        viewProvider.printMessage("Choose rating 'G', 'PG', 'PG-13', 'R', 'NC-17': ");
        Rating rating = Rating.valueOf(viewProvider.readString());
        viewProvider.printMessage("Add special features 'Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes': ");
        String specialFeatures = viewProvider.readString();
        Set<Byte> categoriesId = addCategoriesToFilm();
        Set<Short> actorsId = addActorsToFilm();
        FilmRegistration filmRegistration = FilmRegistration.builder()
                .withTitle(title)
                .withDescription(description)
                .withReleaseYear(releaseYear)
                .withLanguageId(languageId)
                .withRentalDuration(rentalDuration)
                .withRentalRate(rentalRate)
                .withLength(length)
                .withReplacementCost(replacementCost)
                .withRating(rating)
                .withSpecialFeatures(specialFeatures)
                .withCategoriesId(categoriesId)
                .withActorsId(actorsId)
                .build();
        applicationService.addNewFilmToInventories(filmRegistration);
    }

    private Set<Short> addActorsToFilm() {
        Set<Short> actorsId = new HashSet<>();
        viewProvider.printMessage(ACTOR_ADDING);
        while (true) {
            short actorId = viewProvider.readShort();
            if (actorId == 0) {
                break;
            } else {
                actorsId.add(actorId);
            }
        }
        return actorsId;
    }

    private Set<Byte> addCategoriesToFilm() {
        Set<Byte> categoriesId = new HashSet<>();
        viewProvider.printMessage(CATEGORY_ADDING);
        while (true) {
            byte categoryId = viewProvider.readByte();
            if (categoryId == 0) {
                break;
            } else {
                categoriesId.add(categoryId);
            }
        }
        return categoriesId;
    }
}
