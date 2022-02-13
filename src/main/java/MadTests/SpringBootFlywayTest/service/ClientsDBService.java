package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.ClientDTO;
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
public class ClientsDBService {

    private final OffersRepository offersRepository;
    private final ClientRepository clientRepository;

    public ClientsDBService(OffersRepository offersRepository, ClientRepository clientRepository) {
        this.offersRepository = offersRepository;
        this.clientRepository = clientRepository;
    }


    public void create(OfferEntity offer) {
        offersRepository.save(offer);
    }


    public List<OfferEntity> readAll() {
        return offersRepository.findAll();
    }


    public void delete(int id) {
        if (offersRepository.existsById(id)) {
            offersRepository.deleteById(id);
        } else {
            throw new NullPointerException("Услуга не найдена");
        }
    }

    //---------------


    public Integer create(ClientEntity client) {
//        int[] bool = new int[]{0};
//        for (Map.Entry<Date, Date> entry : allTime().entrySet()) {
//
//        }
//        allTime().forEach((date, date2) -> {
//            if (client.getDate().getTime()>=date.getTime() && client.getDate().getTime()<date2.getTime())
//                bool[0]++;
//        });
//        if (bool[0]==0)
//            clientRepository.save(client);
//        if (bool[0]==0)
//            return clientRepository.tempDecision(client.getDate());
        return 0;
    }



    public ClientDTO read(int id) {

        ClientEntity clientEntity = clientRepository.getById(id);
        ClientDTO clientDto = new ClientDTO();
        clientDto.setId(clientEntity.getId());
        clientDto.setName(clientEntity.getName());
        clientDto.setPhone(clientEntity.getPhone());
        return clientDto;
    }



    public Map<Date, Date> allTime() {
        Map<Date, Date> map = new HashMap<>();
//        Map<String, Integer> durations = new HashMap<>();
//        offersRepository.findAll().forEach(offer -> durations.put(offer.getName(),offer.getDuration()));
//        Date current = new Date(new java.util.Date().getTime());
//        clientRepository.allActualClients(current).forEach(client ->
//                map.put(client.getDate(),
//                        new Date(new java.util.Date(client.getDate().getTime()+(60000L*durations.get(client.getOffer()))).getTime())));
        return map;
    }


    public Integer save(ClientDTO client) {
        return 0; //fixme
    }
}

/*
public class Service {

    @Autowired
    ClientRepository clientRepository;

    public Integer save(ClientDTO dto) {
        ClientEntity entity;
        if (dto.getId() == null) {
            entity = new ClientEntity();
        } else {
            Optional<ClientEntity> optional = clientRepository.findById(dto.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            } else {
                throw new NullPointerException("Клиент с указанным идентификатором не найден");
            }
//            entity = clientRepository.findById(dto.getId())
//                    .orElseThrow(()-> new NullPointerException("Клиент с указанным идентификатором не найден"));
        }
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        clientRepository.save(entity);
        return entity.getId();
    }
}
 */