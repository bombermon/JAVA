
package Shop;

public class Sofas extends FurnitureBase {
    private int assembled_length;
    public Sofas(int id, String name, int weight, String color, int width,int length, int cost, int assembled_length) {
        super(id, name, weight, color, width, length,cost);
        this.assembled_length = assembled_length;
    }
    @Override
    public String getInfo() {
        String str = "Название: "+ name + ", Цвет: " + color + ", Вес: " + weight+ ", Длина: "
                + length + ", Длина в собр. виде: " + assembled_length + ", Ширина: " + width + ", Цена: " + cost +  ".";
        return str;
    }
    public int getAssembled_length(){
        return assembled_length;
    }
}

