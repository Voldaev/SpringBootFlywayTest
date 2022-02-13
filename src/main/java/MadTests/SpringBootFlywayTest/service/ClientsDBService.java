package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.ClientDTO;
import MadTests.SpringBootFlywayTest.models.ClientEntity;
import MadTests.SpringBootFlywayTest.repo.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientsDBService {

    private final ClientRepository clientRepository;

    public ClientsDBService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //tests
    public ClientDTO read(int id) {

        ClientEntity clientEntity = clientRepository.getById(id);
        ClientDTO clientDto = new ClientDTO();
        clientDto.setId(clientEntity.getId());
        clientDto.setName(clientEntity.getName());
        clientDto.setPhone(clientEntity.getPhone());
        return clientDto;
    }

    //tests
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

