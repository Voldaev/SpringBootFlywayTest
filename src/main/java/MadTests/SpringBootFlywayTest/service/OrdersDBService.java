package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.OrderDTO;
import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.models.ClientEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrdersDBService {
    public List<TimeWindowDTO> readAll() {
        return null;//fixme
    }

    public List<TimeWindowDTO> read(String offer, TimeWindowDTO window) {
        return null;//fixme
    }

    public boolean create(OrderDTO order) {
        return false;//fixme
    }
}
