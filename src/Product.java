public class Product {

    private int price;
    private String name;
    private int x;
    private int y;
    private int volume;
    private int time=-1;

    public Product(String name, int x, int y) {
        this.name = name;
        price = findPrice(name);
        volume = findVolume(name);
        this.x = x;
        this.y = y;
        this.time = Input.manager.getTime();
    }

    public int getVolume() {
        return volume;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public static int findPrice(String nam) {
        if ("egg".equals(nam)) {
            return 15;
        } else if ("feather".equals(nam)) {
            return 20;
        } else if ("milk".equals(nam)) {
            return 25;
        } else if ("flour".equals(nam)) {
            return 40;
        } else if ("cloth".equals(nam)) {
            return 50;
        } else if ("pocketMilk".equals(nam)) {
            return 60;
        } else if ("bread".equals(nam)) {
            return 80;
        } else if ("shirt".equals(nam)) {
            return 100;
        } else if ("iceCream".equals(nam)) {
            return 120;
        }
        return -1;
    }

    public static int findVolume(String name) {
        if (name.equals("egg") || name.equals("feather") || name.equals("milk"))
            return 1;
        if (name.equals("flour") || name.equals("cloth") || name.equals("pocketMilk"))
            return 2;
        if (name.equals("bread") || name.equals("shirt") || name.equals("iceCream"))
            return 4;
        return -1;
    }

    public int destroyTime() {
        //todo ez
        if (name.equals("egg") || name.equals("feather") || name.equals("milk"))
            return 400;
        else if (name.equals("flour") || name.equals("cloth") || name.equals("pocketMilk"))
            return 5;
        else if (name.equals("bread") || name.equals("shirt") || name.equals("iceCream"))
            return 6;
        return -1;
    }

    public int getTime() {
        return time;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return name+"{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
