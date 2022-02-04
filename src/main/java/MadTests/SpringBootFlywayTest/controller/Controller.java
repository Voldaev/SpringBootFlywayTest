package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dataclass.Client;
import MadTests.SpringBootFlywayTest.dataclass.Offer;
import MadTests.SpringBootFlywayTest.service.DBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    private final DBService DBService;

    public Controller(DBService DBService) {
        this.DBService = DBService;
    }

    @GetMapping(value = "/offers")
    public ResponseEntity<List<Offer>> read() {
        final List<Offer> offers = DBService.readAll();

        return offers != null && !offers.isEmpty() ?
                new ResponseEntity<>(offers, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping(value =  "/offers")
    public ResponseEntity<?> create(@RequestBody Offer offer) {
        DBService.create(offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/offers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = DBService.delete(id);

        return deleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }


//----------------------------------------------------------------------------------

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        final Integer created = DBService.create(client);

        return created > 0 ?
                new ResponseEntity<>(created ,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id)  {
        final Client client = DBService.read(id);

        return client != null ?
                new ResponseEntity<>(client, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<Map<Date, Date>> allTime() {
        final Map<Date, Date> timetable = DBService.allTime();

        return timetable != null && !timetable.isEmpty() ?
                new ResponseEntity<>(timetable, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
