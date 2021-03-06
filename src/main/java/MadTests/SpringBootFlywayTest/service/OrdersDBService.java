package MadTests.SpringBootFlywayTest.service;

import MadTests.SpringBootFlywayTest.dto.OrderDTO;
import MadTests.SpringBootFlywayTest.dto.TimeWindowDTO;
import MadTests.SpringBootFlywayTest.dto.WantedInputDTO;
import MadTests.SpringBootFlywayTest.models.OfferEntity;
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

    public List<TimeWindowDTO> readAll() {
        List<OrderEntity> allActualOrders = ordersRepository.allBlocked();
        List<TimeWindowDTO> windows = new ArrayList<>(allActualOrders.size());
        LocalDateTime start = null;
        for (OrderEntity orderEntity : allActualOrders) {
            if (start == null) {
                windows.add(TimeWindowDTO.builder().start(LocalDateTime.now()).end(orderEntity.getStart()).build());
            }
            if (start != null) {
                LocalDateTime end = orderEntity.getStart();
                if (start.isBefore(end)) {
                    windows.add(
                            TimeWindowDTO.builder()
                                    .start(start)
                                    .end(end)
                                    .build()
                    );
                }
            }
            start = orderEntity.getStart().plusMinutes(orderEntity.getOfferEntity().getDuration());
        }
        if (start != null) {
            windows.add(new TimeWindowDTO(start, LocalTime.MIDNIGHT.atDate(LocalDate.now().plusDays(1))));
        } else {
            windows.add(new TimeWindowDTO(LocalDateTime.now(), LocalTime.MIDNIGHT.atDate(LocalDate.now().plusDays(1))));
        }
        return windows;
    }

    public List<TimeWindowDTO> read(WantedInputDTO wanted) {
        int duration = 0;
        List<OfferEntity> offers = offersRepository.findAll();
        for (OfferEntity of :
                offers) {
            if (of.getName().equals(wanted.getOffer()))
                duration = of.getDuration();
        }
        if (duration == 0)
            throw new NullPointerException("???????????? ???? ??????????????");
        if (wanted.getHour() == -1)
            wanted.setHour(LocalDateTime.now().getHour());
        if (wanted.getMinute() == -1)
            wanted.setMinute(LocalDateTime.now().getMinute());
        TimeWindowDTO targetWindow = new TimeWindowDTO(LocalTime.of(wanted.getHour(), wanted.getMinute()).atDate(LocalDate.now()),
                LocalTime.MIDNIGHT.atDate(LocalDate.now().plusDays(1)));
        //
        List<TimeWindowDTO> windows = readAll();
        List<TimeWindowDTO> result = new ArrayList<>();
        int finalDuration = duration;
        windows.forEach(window -> {
            if (window.getStart().isBefore(targetWindow.getEnd()) && window.getEnd().isAfter(targetWindow.getStart())) {
                TimeWindowDTO test = new TimeWindowDTO();
                test.setStart(window.getStart().isBefore(targetWindow.getStart()) ? targetWindow.getStart() : window.getStart());
                test.setEnd(window.getEnd().isAfter(targetWindow.getEnd()) ? targetWindow.getEnd() : window.getEnd());
                if (test.getStart().plusMinutes(finalDuration).isBefore(test.getEnd()) ||
                        test.getStart().plusMinutes(finalDuration).isEqual(test.getEnd())) {
                    result.add(test);
                }
            }
        });
        return result;
    }

    public void create(OrderDTO order) {
        OrderEntity entity = new OrderEntity();

        entity.setClientEntity(clientsRepository.getById(order.getClient_id()));
        entity.setOfferEntity(offersRepository.getById(order.getOffer_id()));
        entity.setStart(order.getStart());

        List<TimeWindowDTO> windows = readAll();
        TimeWindowDTO target = new TimeWindowDTO(entity.getStart(),entity.getStart().plusMinutes(entity.getOfferEntity().getDuration()));
        boolean approved = false;
        for (TimeWindowDTO window : windows) {
            if (!approved && (target.getStart().isEqual(window.getStart()) || target.getStart().isAfter(window.getStart()))
                    && (target.getEnd().isEqual(window.getEnd()) || target.getEnd().isBefore(window.getEnd())))
                approved = true;
        }
        if (approved)
        ordersRepository.save(entity);
        else throw new NullPointerException("???????????????????????? ?????????????????? ????????????????????");
    }
}
