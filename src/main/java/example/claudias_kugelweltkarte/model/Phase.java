package example.claudias_kugelweltkarte.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


@Entity
@Data
@NoArgsConstructor
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int phaseNumber;
    @Column(columnDefinition = "Text")
    private String gridState;

    public String serialized(Grid grid) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(grid);
    }

    public Grid restore() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(gridState, Grid.class);
    }

}
