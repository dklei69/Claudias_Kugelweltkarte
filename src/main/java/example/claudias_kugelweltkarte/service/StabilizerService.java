package example.claudias_kugelweltkarte.service;

import example.claudias_kugelweltkarte.repository.StabilizerConfigRepository;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StabilizerService {
    private int numberOfStabilizer = 25;
    private StabilizerConfigRepository stabilizerConfigRepository;



    @Autowired
    public StabilizerService(StabilizerConfigRepository stabilizerConfigRepository)  {
    this.stabilizerConfigRepository = stabilizerConfigRepository;
    }

    public void setNumberOfStabilizers(int numbersOfStabilizers){

        if ( numbersOfStabilizers>25 || numbersOfStabilizers<0){
            throw new IllegalArgumentException("Ungültige Anzahl");
        }
        this.numberOfStabilizer =numbersOfStabilizers;
    }

    public int getNumberOfStabilizers() {
        return numberOfStabilizer;
    }

    public void decrementStabilizers(){
        if (numberOfStabilizer == 0){
            throw new IllegalArgumentException("Keine Stabilisatoren übrig");
        } else {

        numberOfStabilizer --;}
    }

    public void incrementStabilizers(){
        numberOfStabilizer ++;
    }

    /*public boolean activateStabilizer(int x, int y, Grid currentGrid){
        Cell[][] cells =currentGrid.getCells();
        if (numberOfStabilizer>0){
            numberOfStabilizer --;
            cells[x][y].setStabilized(true);

        } return cells[x][y].isStabilized();
    }

    public boolean deactivateStabilizer(int x, int y, Grid currentGrid) {
        Cell[][] cells = currentGrid.getCells();
        if(cells[x][y].isStabilized()){
            numberOfStabilizer ++;
            cells[x][y].setStabilized(false);
        } return cells[x][y].isStabilized();
    }*/
}
