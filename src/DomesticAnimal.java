import java.util.Random;

public abstract class DomesticAnimal {
    protected int buyPrice;
    protected int sellPrice;
    protected int capacity;
    protected String productName;
    //todo ez
    private double health;
    private int x;
    private int y;
    private int lastTimeProduce;

    public DomesticAnimal(double health, int buyprice, int sellPrice, int capacity, String productName) {

        this.health = health;
        this.buyPrice = buyprice;
        this.sellPrice = sellPrice;
        this.capacity = capacity;
        Random random = new Random();
        x = random.nextInt(6);
        y = random.nextInt(6);
        lastTimeProduce = Input.manager.getTime();
        this.productName = productName;

    }

    //todo ez

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    abstract public int getTimeNeedToProduce();

    public int getLastTimeProduce() {
        return lastTimeProduce;
    }

    public void setLastTimeProduce(int lastTimeProduce) {
        this.lastTimeProduce = lastTimeProduce;
    }

    public void print() {
        String s = "";
        if (this.getClass() == Hen.class)
            s += "Hen";
        else if (this.getClass() == Turkey.class)
            s += "Turkey";
        else if (this.getClass() == Buffalo.class)
            s += "Buffalo";
        s += this.toString();
        System.out.println(s);
    }

    @Override
    public String toString() {
        return "{" +
                "health=" + health +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public String getName(){
        if (this.getClass()==Hen.class)
            return "hen";
        if (this.getClass()==Buffalo.class)
            return "hen";
        if (this.getClass()==Turkey.class)
            return "hen";
        return "";
    }
}
