package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dataclass.Client;
import MadTests.SpringBootFlywayTest.dataclass.Offer;
import MadTests.SpringBootFlywayTest.repo.ClientRepository;
import MadTests.SpringBootFlywayTest.repo.OffersRepository;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DBServiceImpl implements DBService {

    private final OffersRepository offersRepository;
    private final ClientRepository clientRepository;

    public DBServiceImpl(OffersRepository offersRepository, ClientRepository clientRepository) {
        this.offersRepository = offersRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Offer offer) {
        offersRepository.save(offer);
    }

    @Override
    public List<Offer> readAll() {
        return offersRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        if (offersRepository.existsById(id)) {
            offersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //---------------

    @Override
    public Integer create(Client client) {
        int[] bool = new int[]{0};
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
    public Client read(int id) {
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
