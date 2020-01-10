package org.enigmatic.elonbot.services;

import org.enigmatic.elonbot.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDetailsRepository extends JpaRepository<CategoryModel, Long> {
}
