package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, Integer> {
}