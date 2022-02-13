package MadTests.SpringBootFlywayTest.controller;

import MadTests.SpringBootFlywayTest.dto.OfferDTO;

import MadTests.SpringBootFlywayTest.service.OffersDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OffersController {

    @Autowired
    OffersDBService offersDBService;

    @GetMapping(value = "/offers")
    public List<OfferDTO> getAll() {
        return offersDBService.readAll();
    }

    @PostMapping(value = "/offers")
    public Integer createOffer(@RequestBody OfferDTO offer) {
        return offersDBService.create(offer);
    }
}
