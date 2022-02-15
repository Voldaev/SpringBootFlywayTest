package MadTests.SpringBootFlywayTest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class WantedInputDTO {
    private String offer_name;
    private Integer hours;
    private Integer minutes;
}
