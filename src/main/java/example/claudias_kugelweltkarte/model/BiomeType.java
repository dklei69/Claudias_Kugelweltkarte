package example.claudias_kugelweltkarte.model;

public enum BiomeType {
    WATER ("#ff7f24", "resources/static/images/water.png"),
    MOUNTAIN ("#bebebe", "resources/static/images/mountain.png"),
    PLAIN("#9acd32", "/images/plain.jpg"),
    SPHERE("#000000","/images/sphere.jpg");

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
