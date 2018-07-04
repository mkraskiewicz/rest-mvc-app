package com.mkraskiewicz.repositories;

import com.mkraskiewicz.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maciej on 04/07/2018
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
