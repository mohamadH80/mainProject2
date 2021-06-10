import java.util.Random;

public class Cat {
    static final int buyPrice = 150;
    static final int sellPrice = 75;
    private int x;
    private int y;
    private int catLevel;

    public Cat() {
        catLevel = 1;
        Random random=new Random();
        x=random.nextInt(6);
        y=random.nextInt(6);

    }

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
        return "Cat{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
