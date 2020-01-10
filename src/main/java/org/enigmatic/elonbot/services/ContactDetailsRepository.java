package org.enigmatic.elonbot.services;

import org.enigmatic.elonbot.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactModel, Long> {
    Optional<ContactModel> findById(Long id);

    Optional<ContactModel> findByChatId(Long chatId);

    Optional<ContactModel> findByUserId(Integer userId);

    Optional<ContactModel> findByPhoneNumber(String phoneNumber);
}
