package org.anch.service;

import jakarta.persistence.Query;
import org.anch.controller.FrontController;
import org.anch.dao.*;
import org.anch.dao.impl.*;
import org.anch.dto.CustomerRegistration;
import org.anch.dto.FilmRegistration;
import org.anch.entity.*;
import org.anch.utils.EntityScanner;
import org.anch.view.ViewProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceImplTest {

    private static ApplicationServiceImpl applicationService;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUpAll() {
        sessionFactory = getSessionFactory();

        try (Scanner scanner = new Scanner(System.in);) {
            ActorDao actorDao = new ActorDaoImpl(sessionFactory);
            AddressDao addressDao = new AddressDaoImpl(sessionFactory);
            CategoryDao categoryDao = new CategoryDaoImpl(sessionFactory);
            CustomerDao customerDao = new CustomerDaoImpl(sessionFactory);
            FilmDao filmDao = new FilmDaoImpl(sessionFactory);
            InventoryDao inventoryDao = new InventoryDaoImpl(sessionFactory);
            LanguageDao languageDao = new LanguageDaoImpl(sessionFactory);
            PaymentDao paymentDao = new PaymentDaoImpl(sessionFactory);
            RentalDao rentalDao = new RentalDaoImpl(sessionFactory);
            StaffDao staffDao = new StaffDaoImpl(sessionFactory);
            StoreDao storeDao = new StoreDaoImpl(sessionFactory);
            ViewProvider viewProvider = new ViewProvider(scanner);

            applicationService = new ApplicationServiceImpl(
                    actorDao,
                    addressDao,
                    categoryDao,
                    customerDao,
                    filmDao,
                    inventoryDao,
                    languageDao,
                    paymentDao,
                    rentalDao,
                    staffDao,
                    storeDao);
        }

//        runSqlScriptFile("src/test/resources/no_import.sql");

        GenerateData();
    }



    private static void runSqlScriptFile(String filePath) {
        String sqlScript = null;
        try {
            sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] statements = sqlScript.split(";\\s*\\n"); // Split script into individual statements
            System.out.println("SCRIPT: ");

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            for (String statement : statements) {
                System.out.println(statement); // Optional: print each statement for debugging
                if (!statement.trim().isEmpty()) {
                    Query query = session.createNativeQuery(statement);
                    query.executeUpdate();
                }
            }

            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .configure("hibernate.test.cfg.xml");
        EntityScanner.getEntities().forEach(configuration::addAnnotatedClass);

        return configuration.buildSessionFactory();
    }


    public static void GenerateData() {
        StoreDao storeDao = new StoreDaoImpl(sessionFactory);
        AddressDao addressDao = new AddressDaoImpl(sessionFactory);
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        CityDao cityDao = new CityDaoImpl(sessionFactory);
        LanguageDao languageDao = new LanguageDaoImpl(sessionFactory);
        ActorDao actorDao = new ActorDaoImpl(sessionFactory);
        CategoryDao categoryDao = new CategoryDaoImpl(sessionFactory);

        Country country = Country.builder()
                .withCountry("Test Country")
                .build();

        countryDao.save(country);

        City city = City.builder()
                .withCity("Test City")
                .withCountry(country)
                .build();

        cityDao.save(city);


        Address address = Address.builder()
                .withAddress("Test Address")
                .withAddress2("Test Address 2")
                .withCity(city)
                .withDistrict("Test District")
                .withPostalCode("12345")
                .withPhone("1234567890")
                .build();

        addressDao.save(address);

        Store store = Store.builder()
                .withAddress(address)
                .build();

        storeDao.save(store);

        Language language = Language.builder()
                .withName("Test language")
                .build();

        languageDao.save(language);

        Actor actor = Actor.builder()
                .withFirstName("Test First Name")
                .withLastName("Test Last Name")
                .build();

        actorDao.save(actor);

        Category category = Category.builder()
                .withName("Test Category")
                .build();

        categoryDao.save(category);
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveCustomer() {

        CustomerRegistration customerRegistration = CustomerRegistration.builder()
                .withFirstName("Test First Name")
                .withLastName("Test Last Name")
                .withStoreId(1)
                .withAddressId(1)
                .withEmail("test@gmail.com")
                .withIsActive(true)
                .build();



        applicationService.saveCustomer(customerRegistration);
    }

    @Test
    void returnRentedFilmToStore() {
    }

    @Test
    void rentNewFilm() {
        FilmDao filmDao = new FilmDaoImpl(sessionFactory);

        FilmRegistration filmForRent = FilmRegistration.builder()
                .withTitle("Test Film")
                .withDescription("Test Description")
                .withLanguageId(1)
                .withRentalDuration(3)
                .withRentalRate(4.99)
                .withLength(120)
                .withReplacementCost(19.99)
                .withActorsId(Set.of(1))
                .withCategoriesId(Set.of(1))
                .withSpecialFeatures("Trailers")
                .withRating(Rating.PG)
                .build();


        Integer filmId = applicationService.addNewFilmToInventories(filmForRent);


        filmDao.findById(filmId).ifPresent(film -> {
            assertEquals(filmForRent.getTitle(), film.getTitle());
            assertEquals(filmForRent.getDescription(), film.getDescription());
            assertEquals(filmForRent.getLanguageId(), film.getLanguage().getId());
            assertEquals(filmForRent.getRentalDuration(), film.getRentalDuration());
            assertEquals(filmForRent.getRentalRate(), film.getRentalRate());
            assertEquals(filmForRent.getLength(), film.getLength());
            assertEquals(filmForRent.getReplacementCost(), film.getReplacementCost());
            assertEquals(filmForRent.getRating(), film.getRating());
        });
    }

    @Test
    void addNewFilmToInventories() {
    }
}