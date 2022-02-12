package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.ClientDto;
import MadTests.SpringBootFlywayTest.models.ClientEntity;
import MadTests.SpringBootFlywayTest.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    ClientRepository clientRepository;

    public Integer save(ClientDto dto) {
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
