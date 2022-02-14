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

import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    OrdersDBService ordersDBService;

    @GetMapping(value = "/orders/windows")
    public List<TimeWindowDTO> getAllFreeTime() {
        return ordersDBService.readAll();
    }

    @GetMapping(value = "/orders/window")
    public List<TimeWindowDTO> getFreeTimeInWindow(@RequestBody String offer, TimeWindowDTO window) {
        return ordersDBService.read(offer, window);
    }

    @PostMapping(value = "/orders")
    public void createOrder(@RequestBody OrderDTO order) {
        ordersDBService.create(order);
    }

    @GetMapping(value = "/orders") //debug
    public List<OrderEntity> debug(){ return ordersDBService.debug(); }
}
