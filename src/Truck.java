import java.util.Map;

public class Truck {

    private int truckCapacity;
    private int truckLevel;
    Map<String,Integer> productInTruck;


    public void upgradeTruck()
    {
        truckLevel+=1;
    }

    public void setProductInTruck(Map<String, Integer> productInTruck) {
        this.productInTruck = productInTruck;
    }

    public Map<String, Integer> getProductInTruck() {
        return productInTruck;
    }

    public Truck()
    {
        this.truckCapacity = 10;
        this.truckLevel = 1;
    }

    public int getTruckCapacity()
    {
        return truckCapacity;
    }

    public int getTruckLevel() {
        return truckLevel;
    }

    public void setTruckCapacity(int truckCapacity) {
        this.truckCapacity = truckCapacity;
    }

    public void setTruckLevel(int truckLevel) {
        this.truckLevel = truckLevel;
    }


}
