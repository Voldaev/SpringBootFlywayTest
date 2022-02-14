package MadTests.SpringBootFlywayTest.service;


import MadTests.SpringBootFlywayTest.dto.OrderDTO;
import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.models.OrderEntity;
import MadTests.SpringBootFlywayTest.repo.ClientsRepository;
import MadTests.SpringBootFlywayTest.repo.OffersRepository;
import MadTests.SpringBootFlywayTest.repo.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class OrdersDBService {

    private final OrdersRepository ordersRepository;
    private final ClientsRepository clientsRepository;
    private final OffersRepository offersRepository;

    public OrdersDBService(OrdersRepository ordersRepository, ClientsRepository clientsRepository, OffersRepository offersRepository) {
        this.ordersRepository = ordersRepository;
        this.clientsRepository = clientsRepository;
        this.offersRepository = offersRepository;
    }
    public List<OrderEntity> debug() {
        return ordersRepository.findAll();
    }

    public List<TimeWindowDTO> readAll() {
        Map<LocalDateTime, Integer> allBlocked = ordersRepository.allBlocked();
        TreeMap<LocalDateTime, Integer> sortedBlocked = new TreeMap<>(allBlocked);
        List<TimeWindowDTO> windows = new ArrayList<>();
        if (sortedBlocked.size()>0) {
            windows.add(new TimeWindowDTO(
                    LocalDateTime.now(),
                    sortedBlocked.firstKey()));
            for (Map.Entry<LocalDateTime, Integer> entry : sortedBlocked.entrySet()) {
                windows.set(windows.size()-1,new TimeWindowDTO(windows.get(windows.size()-1).getStart(), entry.getKey()));
                windows.add(new TimeWindowDTO(entry.getKey().plusMinutes(entry.getValue().longValue()),
                        entry.getKey().plusMinutes(entry.getValue().longValue())));
            }
            windows.set(windows.size()-1,new TimeWindowDTO(windows.get(windows.size()-1).getStart(),
                    LocalTime.MIDNIGHT.atDate(LocalDate.now().plusDays(1))));
        } else {
            windows.add(new TimeWindowDTO(LocalDateTime.now(),LocalTime.MIDNIGHT.atDate(LocalDate.now().plusDays(1))));
        }
        return windows;
    }

    public List<TimeWindowDTO> read(String offer, TimeWindowDTO targetWindow) {
        Integer duration = ordersRepository.duration(offer);
        List<TimeWindowDTO> windows = readAll();
        List<TimeWindowDTO> result = new ArrayList<>();
        windows.forEach(window -> {
            if (window.getStart().isBefore(targetWindow.getEnd()) && window.getEnd().isAfter(targetWindow.getStart())) {
                TimeWindowDTO test = new TimeWindowDTO();
                test.setStart(window.getStart().isBefore(targetWindow.getStart()) ? targetWindow.getStart() : window.getStart());
                test.setEnd(window.getEnd().isAfter(targetWindow.getEnd()) ? targetWindow.getEnd() : window.getEnd());
                if (test.getStart().plusMinutes(duration).isBefore(test.getEnd()) ||
                        test.getStart().plusMinutes(duration).isEqual(test.getEnd())) {
                    result.add(test);
                }
            }
        });
        return result;
    }

    public void create(OrderDTO order) {
//        OrderEntity entity = new OrderEntity();
//        entity.setClientEntity(clientsRepository.getById(order.getClient_id()));
//        entity.setOffer_id(offersRepository.getById(order.getOffer_id()));
//        entity.setStart(order.getStart());
//        ordersRepository.save(entity);
    }
}
