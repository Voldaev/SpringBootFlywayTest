package MadTests.SpringBootFlywayTest.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class OrderDTO {
//    private Integer id;
    private Integer client_id;
    private Integer offer_id;
    private LocalDateTime start;

}
