package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

//    @Query(value = "select * from clients where start > :date", nativeQuery = true)
//    List<ClientEntity> allActualClients(@Param("date") Date date);
//
//    @Query(value = "select id from clients where start = :date", nativeQuery = true)
//    Integer tempDecision(@Param("date") Date date);

//    @Query(value = "select o from OfferEntity o where o.name like :wa");

    /* tests
   select o.* from orders o inner join offers of on o.offer_id = of.id
    where date_start + concat(of.duration,' minutes')::interval > current_timestamp;

     */
}