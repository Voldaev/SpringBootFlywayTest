package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dto.ClientDTO;
import MadTests.SpringBootFlywayTest.service.ClientsDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientsController {

    @Autowired
    ClientsDBService clientsDBService;

    @PostMapping(value = "/clients/save")
    public Integer saveClient(@RequestBody ClientDTO client) {
        return clientsDBService.save(client);
    }

    @GetMapping(value = "/clients/{id}")
    public ClientDTO getClient(@PathVariable(name = "id") int id) {
        return clientsDBService.read(id);
    }

}
