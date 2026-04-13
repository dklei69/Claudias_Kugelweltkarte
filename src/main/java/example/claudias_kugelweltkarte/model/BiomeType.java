package example.claudias_kugelweltkarte.model;

public enum BiomeType {
    WATER ("#ff7f24", "/image/water.png"),
    MOUNTAIN ("#bebebe", "/image/mountain.png"),
    PLAIN("#9acd32", "/image/plain.jpg"),
    SPHERE("#000000","/image/shpere.jpg");

    private final String color;
    private final String imagePath;

    BiomeType(String color, String imagePath) {
        this.color = color;
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public String getImagePath() {
        return imagePath;
    }
}
