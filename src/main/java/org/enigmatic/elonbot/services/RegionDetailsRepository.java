package org.enigmatic.elonbot.services;

        import org.enigmatic.elonbot.model.RegionModel;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface RegionDetailsRepository extends JpaRepository<RegionModel, Long> {
}
