package MadTests.SpringBootFlywayTest.dataclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "offers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offer {

    @Id
    @Column(name = "id")
    @SequenceGenerator( name = "jpaSequence", sequenceName = "CUNNING_SEQUENCE", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private Integer id;

    @Column(name = "offer")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Offer offer = (Offer) o;
        return id != null && Objects.equals(id, offer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
