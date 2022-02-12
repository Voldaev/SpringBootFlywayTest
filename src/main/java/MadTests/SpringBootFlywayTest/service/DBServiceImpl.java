package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.models.ClientEntity;
import MadTests.SpringBootFlywayTest.models.OfferEntity;
import MadTests.SpringBootFlywayTest.repo.ClientRepository;
import MadTests.SpringBootFlywayTest.repo.OffersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DBServiceImpl implements DBService {

    private final OffersRepository offersRepository;
    private final ClientRepository clientRepository;

    public DBServiceImpl(OffersRepository offersRepository, ClientRepository clientRepository) {
        this.offersRepository = offersRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(OfferEntity offer) {
        offersRepository.save(offer);
    }

    @Override
    public List<OfferEntity> readAll() {
        return offersRepository.findAll();
    }

    @Override
    public void delete(int id) {
        if (offersRepository.existsById(id)) {
            offersRepository.deleteById(id);
        } else {
            throw new NullPointerException("Услуга не найдена");
        }
    }

    //---------------

    @Override
    public Integer create(ClientEntity client) {
        int[] bool = new int[]{0};
        for (Map.Entry<Date, Date> entry : allTime().entrySet()) {

        }
        allTime().forEach((date, date2) -> {
            if (client.getDate().getTime()>=date.getTime() && client.getDate().getTime()<date2.getTime())
                bool[0]++;
        });
        if (bool[0]==0)
            clientRepository.save(client);
        if (bool[0]==0)
            return clientRepository.tempDecision(client.getDate());
        return 0;
    }


    @Override
    public ClientEntity read(int id) {
        return clientRepository.getById(id);
    }


    @Override
    public Map<Date, Date> allTime() {
        Map<Date, Date> map = new HashMap<>();
        Map<String, Integer> durations = new HashMap<>();
        offersRepository.findAll().forEach(offer -> durations.put(offer.getName(),offer.getDuration()));
        Date current = new Date(new java.util.Date().getTime());
        clientRepository.allActualClients(current).forEach(client ->
                map.put(client.getDate(),
                        new Date(new java.util.Date(client.getDate().getTime()+(60000L*durations.get(client.getOffer()))).getTime())));
        return map;
    }


}
