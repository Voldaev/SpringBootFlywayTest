package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, Integer> {
}