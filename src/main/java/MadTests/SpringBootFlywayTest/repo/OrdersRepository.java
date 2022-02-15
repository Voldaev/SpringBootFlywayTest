package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "select o.* from orders o inner join offers of on o.offer_id = of.offer_id" +
            " where date_start + concat(of.duration,' minutes')::::interval > current_timestamp", nativeQuery = true)
    List<OrderEntity> allBlocked();

}