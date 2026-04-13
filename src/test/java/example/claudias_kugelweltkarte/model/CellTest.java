package example.claudias_kugelweltkarte.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void stabilizedCellShouldNotChangeBiome(){
        Cell cell = new Cell(0,0, BiomeType.PLAIN);
        cell.setStabilized(true);
       cell.setBiomeType(BiomeType.WATER);
       assertEquals(BiomeType.PLAIN, cell.getBiomeType());

    }

    @Test
    void fixedCellShouldNotChangeBiome(){
        Cell cell = new Cell(0,0, BiomeType.WATER);
        cell.setFixed(true);
        cell.setBiomeType(BiomeType.MOUNTAIN);
        assertEquals(BiomeType.WATER,cell.getBiomeType());
    }

    @Test
    void changesCellWhenNotFixedAndNotStabilized(){
        Cell cell = new Cell(0,0,BiomeType.MOUNTAIN);
        cell.setFixed(false);
        cell.setStabilized(false);
        cell.setBiomeType(BiomeType.WATER);
        assertEquals(BiomeType.WATER,cell.getBiomeType());
    }
}