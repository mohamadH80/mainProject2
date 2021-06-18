import java.util.Map;
import java.util.TreeMap;

public class Truck {

    Map<String, Integer> productInTruck;
    private int truckCapacity;
    private int truckLevel;


    public Truck() {
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
        truckCapacity=15;
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

    public int getTruckLevel() {
        return truckLevel;
    }

    public void setTruckLevel(int truckLevel) {
        this.truckLevel = truckLevel;
    }
}
