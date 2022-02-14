package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dto.OrderDTO;
import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.models.OrderEntity;
import MadTests.SpringBootFlywayTest.service.OrdersDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    OrdersDBService ordersDBService;

    @GetMapping(value = "/orders/windows")
    public List<TimeWindowDTO> getAllFreeTime() {
        return ordersDBService.readAll();
    }

    @GetMapping(value = "/orders/wanted") //something wrong with request body recognition fixme
    public List<TimeWindowDTO> getFreeTimeInWindow(@RequestBody String offer, LocalDateTime start, LocalDateTime end) {
        return ordersDBService.read(offer, start, end);
    }

    @PostMapping(value = "/orders")
    public void createOrder(@RequestBody OrderDTO order) {
        ordersDBService.create(order);
    }

}
