package MadTests.SpringBootFlywayTest.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "clients")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //todo это тут не нужно!
//этот костыль был полезен. tests
public class ClientEntity {

    @Id
    @Column(name =  "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

}
