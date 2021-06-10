import java.util.Random;

public abstract class WildAnimal {

    protected double health;
    protected int sellPrice;
    protected int capacity;
    protected double MAX_HEALTH;
    protected int x;
    protected int y;
    private int prisonTime;

    public WildAnimal(double health, int price) {
        this.health = health;
        this.sellPrice = price;
        MAX_HEALTH = health;
        capacity = 15;
        Random random = new Random();
        x = random.nextInt(6);
        y = random.nextInt(6);
        prisonTime = -1;
    }

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

    public int getCapacity() {
        return capacity;
    }

    public void setPrisonTime(int prisonTime) {
        this.prisonTime = prisonTime;
    }

    public int getPrisonTime() {
        return prisonTime;
    }

    public String getName(){
        if (this.getClass()==Bear.class)
            return "Bear";
        if (this.getClass()==Tiger.class)
            return "Tiger";
        if (this.getClass()==Lion.class)
            return "Lion";
        return "";
    }

    abstract public void print();



}
