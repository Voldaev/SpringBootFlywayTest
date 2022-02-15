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
    private String offer;
    private Integer hour;
    private Integer minute;
}
