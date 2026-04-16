package example.claudias_kugelweltkarte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Random;


@Data
@NoArgsConstructor
public class Grid {
    private Cell[][] cells;
    private int height;
    private int width;
    @JsonIgnore
    private final Random random = new Random();


    public Grid(int height, int width) {
        this.cells = new Cell[height][width];
        this.height = height;
        this.width = width;
        initializeCells();
    }
    public void setFixpoint(int x, int y, BiomeType biomeType) {
        cells[x][y] = new Cell(x,y,biomeType);
        cells[x][y].setFixed(true);
    }

    private void initializeCells(){
        BiomeType[] biomes = {
            BiomeType.MOUNTAIN, BiomeType.WATER, BiomeType.PLAIN};
        for (int i=0; i<height;i++){
            for(int j=0; j<width;j++) {
                BiomeType randomBiome = biomes[random.nextInt(biomes.length)];
                cells[i][j] = new Cell(i, j, randomBiome, false);

            }
        }
    }
}
