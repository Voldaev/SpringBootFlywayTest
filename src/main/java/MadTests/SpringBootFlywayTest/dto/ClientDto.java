package MadTests.SpringBootFlywayTest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties({"surname"})
public class ClientDto {

    private Integer id;
    private String name;
    private String phone;

    private String surname;
    @JsonIgnore
    private String firstname;
    @JsonIgnore
    private String middlename;

    public String getFullName() {
        return Arrays.asList(surname, firstname, middlename)
                .stream()
                .filter(x -> x != null)
                .reduce((x1, x2) -> x1 + ", " + x2)
                .orElse("");
    }

}
