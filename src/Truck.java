import java.util.Map;
import java.util.TreeMap;

public class Truck {

    Map<String, Integer> productInTruck;
    private int MAX_Cap = 15;
    private int truckCapacity;
    private int truckLevel;
    private int goTime;
    private int timeInWay;

    public int getMAX_Cap() {
        return MAX_Cap;
    }

    public Truck() {
        timeInWay = 10;
        goTime = -1;
        this.truckCapacity = 10;
        this.truckLevel = 1;
        productInTruck = new TreeMap<String, Integer>();
        productInTruck.put("egg", 0);
        productInTruck.put("feather", 0);
        productInTruck.put("milk", 0);
        productInTruck.put("flour", 0);
        productInTruck.put("cloth", 0);
        productInTruck.put("pocketMilk", 0);
        productInTruck.put("bread", 0);
        productInTruck.put("shirt", 0);
        productInTruck.put("iceCream", 0);

        productInTruck.put("Bear", 0);
        productInTruck.put("Lion", 0);
        productInTruck.put("Tiger", 0);
        truckCapacity = 15;
    }

    public int getTimeInWay() {
        return timeInWay;
    }

    public int getGoTime() {
        return goTime;
    }

    public void setGoTime(int goTime) {
        this.goTime = goTime;
    }

    public void upgradeTruck() {
        //todo coins
        truckLevel += 1;
    }

    public Map<String, Integer> getProductInTruck() {
        return productInTruck;
    }

    public void setProductInTruck(Map<String, Integer> productInTruck) {
        this.productInTruck = productInTruck;
    }

    public int getTruckCapacity() {
        return truckCapacity;
    }

    public void setTruckCapacity(int truckCapacity) {
        this.truckCapacity = truckCapacity;
    }


}
