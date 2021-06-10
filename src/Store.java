import java.util.Map;
import java.util.TreeMap;

public class Store {
    Map<String, Integer> allProductsCap;
    Map<String, Integer> wildAnimalCap = new TreeMap<String, Integer>(); //todo ez
    private int level;
    private int capacity;


    public Store() {
        level = 1;
        allProductsCap = new TreeMap<String, Integer>();
        wildAnimalCap = new TreeMap<String, Integer>();
        allProductsCap.put("egg", 0);
        allProductsCap.put("feather", 0);
        allProductsCap.put("milk", 0);
        allProductsCap.put("flour", 0);
        allProductsCap.put("cloth", 0);
        allProductsCap.put("pocketMilk", 0);
        allProductsCap.put("bread", 0);
        allProductsCap.put("shirt", 0);
        allProductsCap.put("iceCream", 0);
        capacity = 30;

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRemaining() {
        int c = 0;

        for (String s : allProductsCap.keySet()) {
            c += Product.findVolume(s) * allProductsCap.get(s);
        }
        for (String s : wildAnimalCap.keySet()) {
            c += wildAnimalCap.get(s) * 15;
        }

        return capacity - c;
    }

    public void upgrade() {
        if (Input.manager.getCoins() < 150)
            System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
        else {
            capacity += 20;
            level++;
            Input.manager.setCoins(Input.manager.getCoins() - 150);
        }
    }


}
