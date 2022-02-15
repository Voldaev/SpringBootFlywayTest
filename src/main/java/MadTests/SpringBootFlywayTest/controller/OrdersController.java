package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dto.OrderDTO;
import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.dto.WantedInputDTO;
import MadTests.SpringBootFlywayTest.service.OrdersDBService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersController {
    @Autowired
    OrdersDBService ordersDBService;

    @GetMapping(value = "/orders/windows")
    public List<TimeWindowDTO> getAllFreeTime() {
        return ordersDBService.readAll();
    }


    @GetMapping(value = "/orders/wanted")
    public List<TimeWindowDTO> getFreeTimeInWindow(@RequestParam(name = "offer") String offer,
                                                   @RequestParam(name = "hour", required = false, defaultValue = "-1") String hour,
                                                   @RequestParam(name = "minute", required = false, defaultValue = "-1") String minute) {
        return ordersDBService.read(offer, hour, minute);
    }

    @PostMapping(value = "/orders")
    public void createOrder(@RequestBody OrderDTO order) {
        ordersDBService.create(order);
    }

}
