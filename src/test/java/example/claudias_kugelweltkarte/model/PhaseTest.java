package example.claudias_kugelweltkarte.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhaseTest {

    @Test
    void gridShouldBeCorrectlySerializedAndRestored() throws JsonProcessingException{
        Grid grid = new Grid(50,50);
        Phase phase = new Phase();
        phase.setGridState(phase.serialized(grid));
        Grid restoredGrid = phase.restore();
        assertEquals(grid.getHeight(), restoredGrid.getHeight());
        assertEquals(grid.getWidth(), restoredGrid.getWidth());
    }
}