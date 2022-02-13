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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id",nullable = false)
    private ClientEntity offerEntity;

    @Column(name = "date_start")
    private LocalDateTime start;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id",nullable = false)
//    private ClientEntity clientEntity;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "clientEntity")
//    private Set<OfferEntity> offers;
//
}
