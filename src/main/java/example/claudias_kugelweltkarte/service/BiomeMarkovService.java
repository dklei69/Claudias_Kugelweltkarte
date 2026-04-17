package example.claudias_kugelweltkarte.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.claudias_kugelweltkarte.model.BiomeType;

import example.claudias_kugelweltkarte.model.Cell;
import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.model.Phase;
import example.claudias_kugelweltkarte.repository.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BiomeMarkovService implements MarkovService{

    private final Random random;
    private final PhaseRepository phaseRepository;
    private int phaseNumber = 0;
    private Grid currentGrid;
    private double[][] variation={
        {0.5, 0.1, 0.4}, //Water->Water,Mountain,Plain
        {0.2, 0.5, 0.3}, //Mountain->Water,Mountain,Plain
        {0.4, 0.3, 0.3}  //Plain->Plain,Mountain,Water
    };

    // Konsturktoren

    //Rekursiver Konstruktor
    @Autowired
    public BiomeMarkovService(PhaseRepository phaseRepository){
        this (new Random(), phaseRepository);
    }

    public BiomeMarkovService(Random random, PhaseRepository phaseRepository){
        this.random = random;
        this.phaseRepository= phaseRepository;
        this.currentGrid = new Grid(50,50);
        currentGrid.setFixpoint(25,25,BiomeType.SPHERE);
    }

    public Grid getGrid(){
        return currentGrid;
    }

    @Override
    public int getPhaseNumber(){
        return phaseNumber;
    }
    @Override
    public void nextPhase() {
        currentGrid = calculateNextPhase(currentGrid);
        phaseNumber++;

        Phase phase = new Phase();
        phase.setPhaseNumber(phaseNumber);
        try {
            phase.setGridState(phase.serialized(currentGrid));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        phaseRepository.save(phase);
    }
    @Override
    public void stabilize(int x, int y, int phase){
        Cell cell = currentGrid.getCells()[x][y];
        if (cell.isStabilized()){
            cell.setStabilized(false);
        } else {
            cell.setStabilized(true);
        }
    }

    @Override
    public Grid calculateNextPhase(Grid grid) {
       Grid nextGrid = new Grid(grid.getHeight(), grid.getWidth());
       BiomeType[] biomes = {BiomeType.WATER,BiomeType.MOUNTAIN,BiomeType.PLAIN};
       for(int i = 0; i < grid.getHeight(); i++){
           for (int j = 0; j < grid.getWidth(); j++){
               Cell currentCell = grid.getCells()[i][j];
               BiomeType currentBiome = currentCell.getBiomeType();

               if (currentCell.isFixed()||currentCell.isStabilized()){
                   nextGrid.getCells()[i][j] = currentCell;
               }else{
                   double randomValue = random.nextDouble();
                   double culmulative = 0.0;
                   int biomeIndex = currentBiome.ordinal();
                   double[] probability = variation[biomeIndex];

                   for (int k = 0; k < probability.length; k++){
                       culmulative += probability[k];

                       if(randomValue < culmulative){
                            BiomeType newBiome = biomes[k];
                            nextGrid.getCells()[i][j] = new Cell(i,j,newBiome,false);
                            break;
                       }
                   }
               }
           }

        }
       return nextGrid;
    }

}
