package com.mkraskiewicz.bootstrap;

import com.mkraskiewicz.domain.Category;
import com.mkraskiewicz.domain.Customer;
import com.mkraskiewicz.repositories.CategoryRepository;
import com.mkraskiewicz.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Maciej on 03/07/2018
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadCategories();

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Category Data loaded = " + categoryRepository.count());
    }

    private void loadCustomers(){
        Customer maciej = new Customer();
        maciej.setFirstName("Maciej");
        maciej.setLastName("Kraskiewicz");

        Customer random = new Customer();
        random.setFirstName("Bob");
        random.setLastName("Random");

        Customer anotherRandom = new Customer();
        anotherRandom.setFirstName("John");
        anotherRandom.setLastName("Snow");

        customerRepository.save(maciej);
        customerRepository.save(random);
        customerRepository.save(anotherRandom);

        log.info("Customer Data loaded = " + customerRepository.count());
    }
}
