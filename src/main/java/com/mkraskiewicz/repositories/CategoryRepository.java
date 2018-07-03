package com.mkraskiewicz.repositories;

import com.mkraskiewicz.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maciej on 03/07/2018
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
