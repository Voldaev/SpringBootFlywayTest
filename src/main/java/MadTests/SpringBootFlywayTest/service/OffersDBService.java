package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.OfferDTO;
import MadTests.SpringBootFlywayTest.models.OfferEntity;
import MadTests.SpringBootFlywayTest.repo.OffersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class OffersDBService {

    private final OffersRepository offersRepository;

    public OffersDBService(OffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }

    public List<OfferDTO> readAll() {
        List<OfferEntity> offerList = offersRepository.findAll();
        List<OfferDTO> offerDTOS = new LinkedList<>();
        offerList.forEach(offer -> offerDTOS.add(new OfferDTO(offer.getId(),offer.getName(),offer.getDuration(),offer.getPrice())));
        return offerDTOS;
    }

    public void create(OfferDTO offer) {
        OfferEntity entity = new OfferEntity();
        entity.setName(offer.getName());
        entity.setDuration(offer.getDuration());
        entity.setPrice(offer.getPrice());
        offersRepository.save(entity);
    }

    public void delete(int id) {
        if (offersRepository.existsById(id)) {
            offersRepository.deleteById(id);
        } else {
            throw new NullPointerException("Услуга не найдена");
        }
    }
}
