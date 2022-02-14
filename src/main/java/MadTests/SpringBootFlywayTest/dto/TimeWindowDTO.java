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
public class TimeWindowDTO {
    private LocalDateTime start;
    private LocalDateTime end;

}
