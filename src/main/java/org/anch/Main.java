package org.anch;

import org.anch.controller.FrontController;
import org.anch.dao.*;
import org.anch.dao.impl.*;
import org.anch.service.*;
import org.anch.view.ViewProvider;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);) {
            ActorDao actorDao = new ActorDaoImpl();
            AddressDao addressDao = new AddressDaoImpl();
            CategoryDao categoryDao = new CategoryDaoImpl();
            CustomerDao customerDao = new CustomerDaoImpl();
            FilmDao filmDao = new FilmDaoImpl();
            InventoryDao inventoryDao = new InventoryDaoImpl();
            LanguageDao languageDao = new LanguageDaoImpl();
            PaymentDao paymentDao = new PaymentDaoImpl();
            RentalDao rentalDao = new RentalDaoImpl();
            StaffDao staffDao = new StaffDaoImpl();
            StoreDao storeDao = new StoreDaoImpl();
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
