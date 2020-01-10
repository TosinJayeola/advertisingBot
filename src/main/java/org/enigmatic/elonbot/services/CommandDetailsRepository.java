package org.enigmatic.elonbot.services;

import org.enigmatic.elonbot.model.CommandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandDetailsRepository extends JpaRepository<CommandModel, Integer> {
}
