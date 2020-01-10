package org.enigmatic.elonbot.services;

import org.enigmatic.elonbot.model.AdModel;
import org.enigmatic.elonbot.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdDetailsRepository extends JpaRepository<AdModel, Long> {
    Optional<AdModel> findById(Long id);

    Optional<AdModel> findByContact_Id(Long contactId);
}