package MadTests.SpringBootFlywayTest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Table(name = "offers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OfferEntity {

    @Id
    @Column(name = "id")
//    @SequenceGenerator( name = "jpaSequence", sequenceName = "CUNNING_SEQUENCE", allocationSize = 1) //todo это тут зачем?
//    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence") //todo это тоже?
    @GeneratedValue(strategy = GenerationType.IDENTITY) //todo правильно писать так!
    private Integer id;

    @Column(name = "offer")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY) //todo как соединять сущности!
    @JoinColumn(name = "client_id",nullable = false)
    private ClientEntity clientEntity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Offer offer = (Offer) o;
//        return id != null && Objects.equals(id, offer.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
