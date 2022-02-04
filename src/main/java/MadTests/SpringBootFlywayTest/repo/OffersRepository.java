package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.dataclass.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offer, Integer> {
    //
}
