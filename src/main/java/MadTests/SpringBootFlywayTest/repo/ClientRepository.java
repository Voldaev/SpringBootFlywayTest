package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.dataclass.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "select * from clients where start > :date", nativeQuery = true)
    List<Client> allActualClients(@Param("date") Date date);

    @Query(value = "select id from clients where start = :date", nativeQuery = true)
    Integer tempDecision(@Param("date") Date date);
}