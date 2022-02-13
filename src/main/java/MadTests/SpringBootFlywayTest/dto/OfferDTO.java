package MadTests.SpringBootFlywayTest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class OfferDTO {
    private Integer id;
    private String name;
    private Integer duration;
    private Integer price;
}
