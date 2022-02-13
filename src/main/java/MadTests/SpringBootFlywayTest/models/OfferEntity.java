package MadTests.SpringBootFlywayTest.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "offers")
public class OfferEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tests
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offerEntity")
    private List<OrderEntity> orders;

}
