package MadTests.SpringBootFlywayTest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Table(name = "clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //todo это тут не нужно!
public class ClientEntity {

    @Id
    @Column(name =  "id")
    @SequenceGenerator( name = "jpaAnotherSequence", sequenceName = "ANOTHER_SEQUENCE", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaAnotherSequence")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "offer")
    private String offer;

    @Column(name = "start")
    //todo не используй тип date вместо него
    // правильно использовать LocalDateTime или LocalDate
    // в зависимости от того в каком виде дата храниться в БД!
    private Date date;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "clientEntity")
    private Set<OfferEntity> offers;

//    @Override
//    public boolean equals(Object o) { //todo у сущностей БД не положено использовать переопределение этого метода
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        ClientEntity clientEntity = (ClientEntity) o;
//        return id != null && Objects.equals(id, clientEntity.id);
//    }
//
//    @Override //todo аналогично equals
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
