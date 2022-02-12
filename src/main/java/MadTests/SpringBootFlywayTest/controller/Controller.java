package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dto.ClientDto;
import MadTests.SpringBootFlywayTest.models.ClientEntity;
import MadTests.SpringBootFlywayTest.models.OfferEntity;
import MadTests.SpringBootFlywayTest.service.DBService;
import MadTests.SpringBootFlywayTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    DBService dbService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/offers")
    public List<OfferEntity> read() {
        return dbService.readAll();
    }

    @PostMapping(value =  "/offers")
    public ResponseEntity<?> create(@RequestBody OfferEntity offer) {
        dbService.create(offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/offers/{id}")
    public void delete(@PathVariable(name = "id") int id) {
       dbService.delete(id);
    }


//----------------------------------------------------------------------------------

    @PostMapping(value = "/clients/save")
    public Integer saveClient(@RequestBody ClientDto client) {
        return userService.save(client);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientEntity> read(@PathVariable(name = "id") int id)  {
        final ClientEntity clientEntity = dbService.read(id);

        return clientEntity != null ?
                new ResponseEntity<>(clientEntity, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<Map<Date, Date>> allTime() {
        final Map<Date, Date> timetable = dbService.allTime();

        return timetable != null && !timetable.isEmpty() ?
                new ResponseEntity<>(timetable, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    /*
    * todo какие сервисы реализовать для фронта:
    *  1. получение информации о клиенте по id
    *  2. обновление или создание нового клиента
    *  3. получение списка услуг
    *  4. получение списка свободного времени для записи
    *  4.1. можно добавить чтобы сервис получал на вход параметры для расчета нужного свободного времени
    *       параметры: идентификатор услуги и интервал который клиенту интересен
    *  5. запись на вход идентификатор клиента, услуга, дата и время
    * */
}
