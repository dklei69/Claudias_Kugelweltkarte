package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.model.Grid;


public interface MarkovService {
    Grid getGrid();
    Grid calculateNextPhase(Grid grid);
    void nextPhase();
    void stabilize(int x, int y, int phase);
    int getPhaseNumber();
}
