package Shop;


public class Beds extends FurnitureBase {
    private String mattress;
    public Beds(int id, String name, int weight, String color,int width,int length,int cost,String mattress) {
        super(id, name, weight, color, width, length,cost);
        this.mattress = mattress;
    }
    @Override
    public String getInfo() {
        String str = "Название: "+ name + ", Цвет: " + color + ", Вес: " + weight + ", Длина: " +
                length + ", Наличие матраса:" + mattress+", Ширина: " + width + ", Цена: " + cost + ".";
        return str;
    }
    public String getMattress(){
        return mattress;
    }

}