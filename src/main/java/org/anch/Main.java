package org.anch;

import org.anch.config.SessionFactoryCreator;
import org.anch.controller.FrontController;
import org.anch.dao.*;
import org.anch.dao.impl.*;
import org.anch.service.*;
import org.anch.view.ViewProvider;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryCreator.getSessionFactory();

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

            ApplicationService applicationService = new ApplicationServiceImpl(
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

            FrontController frontController = new FrontController(applicationService, viewProvider);

            frontController.run();
        }
    }
}
