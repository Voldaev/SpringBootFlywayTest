package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "select o.date_start,of.duration from orders o inner join offers of on o.offer_id = of.offer_id" +
            " where date_start + concat(of.duration,' minutes')::::interval > current_timestamp", nativeQuery = true)
    Map<LocalDateTime, Integer> allBlocked();

    @Query(value = "select duration from offers where name = :name", nativeQuery = true)
    Integer duration(@Param("name") String name);

    @Query(value = "select date_start from orders", nativeQuery = true)
    List<Timestamp> allStarts();
}