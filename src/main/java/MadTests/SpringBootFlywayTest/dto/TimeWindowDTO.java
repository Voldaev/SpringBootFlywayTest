package MadTests.SpringBootFlywayTest.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


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


//    //todo пример
//    {
//        List<TimeWindowDTO> list = new ArrayList<>();
////        list.add
//        list.sort(Comparator.comparing(TimeWindowDTO::getStart));
//
//        list.sort(new Comparator<TimeWindowDTO>() {
//            @Override
//            public int compare(TimeWindowDTO o1, TimeWindowDTO o2) {
//                return o1.getStart().compareTo(o2.getStart());
//            }
//        });
//    }

}
