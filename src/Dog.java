import java.util.Random;

public class Dog {
    static final int buyPrice=100;
    static final int sellPrice=50;
    private int dogLevel;
    private int x;
    private int y;

    public Dog() {
        dogLevel = 1;
        Random random=new Random();
        x=random.nextInt(6);
        y=random.nextInt(6);
    }
    //todo ez

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
