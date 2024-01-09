package org.anch.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.anch.dao.*;
import org.anch.dto.CustomerRegistration;
import org.anch.dto.FilmRegistration;
import org.anch.dto.FilmRental;
import org.anch.entity.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;

    @Transactional
    @Override
    public void saveCustomer(CustomerRegistration customerRegistration) {

        Store store = storeDao.findById(customerRegistration.getStoreId()).get();
        Address address = addressDao.findById(customerRegistration.getAddressId()).get();
        Customer customer = Customer.builder()
                .withStore(store)
                .withFirstName(customerRegistration.getFirstName())
                .withLastName(customerRegistration.getLastName())
                .withEmail(customerRegistration.getEmail())
                .withAddress(address)
                .withIsActive(customerRegistration.getIsActive())
                .build();
        customerDao.save(customer);
    }

    @Transactional
    @Override
    public void returnRentedFilmToStore(Integer rentalId) {
        Rental rental = rentalDao.findById(rentalId).get();
        rental.setReturnDate(LocalDateTime.now());
        rentalDao.update(rental);
    }

    @Transactional
    @Override
    public Boolean rentNewFilm(FilmRental filmRental) {

        Inventory availableFilmInventory = null;
        Customer customer = customerDao.findById(filmRental.getCustomerId()).get();
        Staff staff = staffDao.findById(filmRental.getStaffId()).get();

        Byte storeId = customer.getStore().getId();
        List<Inventory> inventories = inventoryDao.findAllInventoriesByFilmId(filmRental.getFilmId(), storeId);

        List<Inventory> rentalInventories = inventories
                .stream()
                .filter(inventory -> !inventory.getRentals().isEmpty())
                .toList();

        if (!rentalInventories.isEmpty()) {
            for (Inventory inventory : rentalInventories) {
                Rental rental = Collections.max(inventory.getRentals(), Comparator.comparing(Rental::getLastUpdate));
                if (!Objects.isNull(rental.getReturnDate())) {
                    availableFilmInventory = inventory;
                    break;
                }
            }
        } else {
            return false;
        }

        Rental rental = Rental.builder()
                .withRentalDate(LocalDateTime.now())
                .withInventory(availableFilmInventory)
                .withCustomer(customer)
                .withStaff(staff)
                .build();

        Integer rentalId = rentalDao.save(rental);
        Rental savedRental = rentalDao.findById(rentalId).get();

        Payment payment = Payment.builder()
                .withCustomer(customer)
                .withStaff(staff)
                .withRental(savedRental)
                .withAmount(filmRental.getAmount())
                .withPaymentDate(LocalDateTime.now())
                .build();
        paymentDao.save(payment);

        return true;
    }

    @Transactional
    @Override
    public void addNewFilmToInventories(FilmRegistration filmRegistration) {

        Language language = languageDao.findById(filmRegistration.getLanguageId()).get();

        Set<Actor> actors = actorDao.findAllActorsByIds(filmRegistration.getActorsId());

        Set<Category> categories = categoryDao.findAllCategoriesByIds(filmRegistration.getCategoriesId());

        Film film = Film.builder()
                .withTitle(filmRegistration.getTitle())
                .withDescription(filmRegistration.getDescription())
                .withReleaseYear(filmRegistration.getReleaseYear())
                .withLanguage(language)
                .withRentalDuration(filmRegistration.getRentalDuration())
                .withRentalRate(filmRegistration.getRentalRate())
                .withLength(filmRegistration.getLength())
                .withReplacementCost(filmRegistration.getReplacementCost())
                .withRating(filmRegistration.getRating())
                .withSpecialFeaturesStr(filmRegistration.getSpecialFeatures())
                .withActors(actors)
                .withCategories(categories)
                .build();

        Short filmId = filmDao.save(film);

    }

}
