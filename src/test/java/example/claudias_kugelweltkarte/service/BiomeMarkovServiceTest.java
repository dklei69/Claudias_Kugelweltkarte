package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.model.BiomeType;
import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.repository.PhaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static example.claudias_kugelweltkarte.model.BiomeType.WATER;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BiomeMarkovServiceTest {

    @Mock
    PhaseRepository phaseRepository;
    @Test
    void calculateNextPhaseShouldReturnGridWithSameDimensions(){
       BiomeMarkovService service = new BiomeMarkovService(new Random(42),phaseRepository);
        Grid currentGrid = service.getGrid();
        Grid nextGrid = service.calculateNextPhase(currentGrid);
        assertEquals(50, nextGrid.getHeight());
        assertEquals(50, nextGrid.getWidth());
        assertEquals(50, nextGrid.getCells().length);
        assertEquals(50, nextGrid.getCells()[0].length);
    }

    @Test
    void fixpointShouldRemainUnchangedAfterPhaseChange(){
        BiomeMarkovService service = new BiomeMarkovService(new Random(42),phaseRepository);
        Grid currentGrid = service.getGrid();
        Grid nextGrid = service.calculateNextPhase(currentGrid);
        assertTrue(nextGrid.getCells()[25][25].isFixed());
        assertEquals(BiomeType.SPHERE,nextGrid.getCells()[25][25].getBiomeType());
    }

    @Test
    void stabilizedCellShouldRemainUnchangedAfterPhaseChange(){
        BiomeMarkovService service = new BiomeMarkovService(new Random(42), phaseRepository);
        Grid currentGrid = service.getGrid();
        // Dummies setzen
        currentGrid.getCells()[10][10].setStabilized(true);
        BiomeType originalBiome = currentGrid.getCells()[10][10].getBiomeType();
        Grid nextGrid = service.calculateNextPhase(currentGrid);
        assertEquals(originalBiome, nextGrid.getCells()[10][10].getBiomeType());
        assertTrue(nextGrid.getCells()[10][10].isStabilized());

    }

    @Test
    void cellsShouldChangeAccordingToPropabilities(){
        BiomeMarkovService service = new BiomeMarkovService(new Random(42), phaseRepository);
        Grid currentGrid = service.getGrid();
        Grid nextGrid = service.calculateNextPhase(currentGrid);
        //System.out.println(nextGrid.getCells()[10][10].getBiomeType());
        assertEquals(BiomeType.WATER, nextGrid.getCells()[10][10].getBiomeType());
    }

}