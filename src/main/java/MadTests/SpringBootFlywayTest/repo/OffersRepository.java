package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<OfferEntity, Integer> {
    //
}
