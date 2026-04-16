package example.claudias_kugelweltkarte.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Cell {
    @Setter(AccessLevel.NONE)
    private BiomeType biomeType;
    private int x;
    private int y;
    private boolean isStabilized;
    private boolean isFixed;

    //Konstruktor
    public Cell(int x, int y, BiomeType biomeType, boolean isStabilized) {
        this.x = x;
        this.y = y;
        this.biomeType = biomeType;
        this.isStabilized = isStabilized;
    }

    public Cell(int x, int y, BiomeType biomeType) {
        this(x, y, biomeType, false);
    }

    public void setBiomeType(BiomeType biomeType) {
        if (!isStabilized && !isFixed) {
            this.biomeType = biomeType;
        }
    }

}
