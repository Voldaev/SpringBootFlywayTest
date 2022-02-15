package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.ClientDTO;
import MadTests.SpringBootFlywayTest.models.ClientEntity;
import MadTests.SpringBootFlywayTest.repo.ClientsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientsDBService {

    private final ClientsRepository clientsRepository;

    public ClientsDBService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ClientDTO read(int id) {

        ClientEntity clientEntity = clientsRepository.getById(id);
        ClientDTO clientDto = new ClientDTO();
        clientDto.setId(clientEntity.getId());
        clientDto.setName(clientEntity.getName());
        clientDto.setPhone(clientEntity.getPhone());
        return clientDto;
    }

    public Integer save(ClientDTO dto) {
        ClientEntity entity;
        if (dto.getId() == null) {
            entity = new ClientEntity();
        } else {
            Optional<ClientEntity> optional = clientsRepository.findById(dto.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            } else {
                throw new NullPointerException("Клиент с указанным идентификатором не найден");
            }
//            entity = clientsRepository.findById(dto.getId())
//                    .orElseThrow(()-> new NullPointerException("Клиент с указанным идентификатором не найден"));
        }
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        clientsRepository.save(entity);
        return entity.getId();
    }
}

