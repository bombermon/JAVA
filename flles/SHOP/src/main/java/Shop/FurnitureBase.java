package Shop;

public class FurnitureBase {
    protected final int id;
    protected final String name;
    protected final int weight;
    protected final String color;
    protected final int width;
    protected final int length;
    protected final int cost;

    public FurnitureBase(int id, String name, int weight, String color,int width,int length, int cost){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.width = width;
        this.length = length;
        this.cost = cost;

    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getWeight(){
        return weight;
    }
    public String getColor(){
        return color;
    }
    public int getWidth(){
        return width;
    }
    public int getLength(){
        return length;
    }
    public int getCost(){
        return cost;
    }
    public String getInfo(){
        String str = name + ", Цвет: " + color + ", Вес: " + weight + ", Длина: "
                + length + ", Цена: " + cost + ", Ширина: " + width + ".";
        return str;
    }
}
