package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.model.Grid;

public interface MarkovService {
    Grid getGrid();
    Grid calculateNextPhase(Grid grid);
    void nextPhase();
}
