package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.model.Grid;
import example.claudias_kugelweltkarte.model.Phase;

public interface MarkovService {
    Grid getGrid();
    Grid calculateNextPhase(Grid grid);
    void nextPhase();
    void stabilize(int x, int y, int phase);
    int getPhaseNumber();
}
