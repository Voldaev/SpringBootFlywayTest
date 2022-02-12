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
public class FreeDto {
    private LocalDateTime start;
    private LocalDateTime end;


//    //todo пример
//    {
//        List<FreeDto> list = new ArrayList<>();
////        list.add
//        list.sort(Comparator.comparing(FreeDto::getStart));
//
//        list.sort(new Comparator<FreeDto>() {
//            @Override
//            public int compare(FreeDto o1, FreeDto o2) {
//                return o1.getStart().compareTo(o2.getStart());
//            }
//        });
//    }

}
