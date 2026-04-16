package example.claudias_kugelweltkarte.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void gridShouldBeInizialisedWithTheCorrectDimentions() {
        Grid grid = new Grid(50, 50);
        assertEquals(50, grid.getWidth());
        assertEquals(50, grid.getHeight());
        assertEquals(50, grid.getCells().length);
        assertEquals(50, grid.getCells()[0].length);
    }
    @Test
    void fixpointShouldBeSetCorrectly(){
        Grid grid = new Grid(50, 50);
    grid.setFixpoint(25,25, BiomeType.SPHERE);
        assertTrue(grid.getCells()[25][25].isFixed());
    }

}