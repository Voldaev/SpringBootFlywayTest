package MadTests.SpringBootFlywayTest.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fixme")
    private Integer clientId;

    @Column(name = "fixme")
    private Integer offerId;

    @Column(name = "start")
    private LocalDateTime start;

//
//    @ManyToOne(fetch = FetchType.LAZY) //todo как соединять сущности!
//    @JoinColumn(name = "client_id",nullable = false)
//    private ClientEntity clientEntity;

    /*
    @Column(name = "start")
    //todo не используй тип date вместо него
    // правильно использовать LocalDateTime или LocalDate
    // в зависимости от того в каком виде дата храниться в БД!
    private Date date;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "clientEntity")
    private Set<OfferEntity> offers;

     */
}
