package MadTests.SpringBootFlywayTest.repo;

import MadTests.SpringBootFlywayTest.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {

}