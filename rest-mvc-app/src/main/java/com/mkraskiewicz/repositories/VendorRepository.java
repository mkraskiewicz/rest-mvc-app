package com.mkraskiewicz.repositories;


import com.mkraskiewicz.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maciej on 09/07/2018
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
