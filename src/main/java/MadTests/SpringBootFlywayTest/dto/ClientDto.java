package MadTests.SpringBootFlywayTest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ClientDto {

    private Integer id;
    private String name;
    private String phone;

}
