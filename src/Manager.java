import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

//todo truck and updates remain
public class Manager {
    static Logger logger = Logger.getLogger(Logger.class.getName());
    ArrayList<DomesticAnimal> domestics = new ArrayList<DomesticAnimal>();
    ArrayList<WildAnimal> wilds = new ArrayList<WildAnimal>();
    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Cat> cats = new ArrayList<Cat>();
    ArrayList<Dog> dogs = new ArrayList<Dog>();
    ArrayList<Factory> factories = new ArrayList<Factory>();

    Truck truck = new Truck();
    Well well = new Well();
    Store store = new Store();
    int[][] grasses = new int[6][6];

    private int coins = 100000;
    private int time = 0;

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
        } else if ("Bear".equals(nam))
            return 200;
        else if ("Lion".equals(nam))
            return 150;
        else if ("Tiger".equals(nam))
            return 250;
        return -1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void pickup(int x, int y) {
        boolean find = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getX() == x && products.get(i).getY() == y) {
                find = true;
                if (store.getRemaining() > products.get(i).getVolume()) {
                    int c = store.allProductsCap.get(products.get(i).getName());
                    store.allProductsCap.put(products.get(i).getName(), c + 1);
                    products.remove(i);
                } else {
                    System.out.println(ConsoleColors.RED + "store is full for " + products.get(i).getName() + "!" + ConsoleColors.RESET);
                }
            }
        }
        if (!find)
            System.out.println(ConsoleColors.RED + "there isn't any product here!" + ConsoleColors.RESET);
    }

    public void well() {
        if (well.getRemaining() == well.getCapacity())
            System.out.println(ConsoleColors.RED + "well is full" + ConsoleColors.RESET);
        else if (well.getTime() == -1)
            well.setTime(time);
        else
            System.out.println(ConsoleColors.RED + "well is getting water!" + ConsoleColors.RESET);
    }

    public void plant(int x, int y) {
        if (well.getRemaining() > 0) {
            well.setRemaining(well.getRemaining() - 1);
            grasses[x][y]++;
        } else
            System.out.println(ConsoleColors.RED + "well is empty!" + ConsoleColors.RESET);

    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void buy(String name) {

        if (name.equalsIgnoreCase("hen")) {
            if (coins < 100)
                System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            else {
                Hen hen = new Hen();
                coins -= hen.buyPrice;
                domestics.add(hen);
                System.out.println(ConsoleColors.YELLOW + "done" + ConsoleColors.RESET);
            }
        } else if (name.equalsIgnoreCase("buffalo")) {
            if (coins < 400)
                System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            else {
                Buffalo buffalo = new Buffalo();
                coins -= buffalo.buyPrice;
                domestics.add(buffalo);
                System.out.println(ConsoleColors.YELLOW + "done" + ConsoleColors.RESET);

            }
        } else if (name.equalsIgnoreCase("turkey")) {
            if (coins < 200)
                System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            else {
                Turkey turkey = new Turkey();
                coins -= turkey.buyPrice;
                domestics.add(turkey);
                System.out.println(ConsoleColors.YELLOW + "done" + ConsoleColors.RESET);

            }
        } else if (name.equalsIgnoreCase("cat")) {

            if (coins < Cat.buyPrice)
                System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            else {
                Cat cat = new Cat();
                coins -= Cat.buyPrice;
                cats.add(cat);
                System.out.println(ConsoleColors.YELLOW + "done" + ConsoleColors.RESET);

            }
        } else if (name.equalsIgnoreCase("dog")) {
            if (coins < Dog.buyPrice)
                System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            else {
                Dog dog = new Dog();
                coins -= Dog.buyPrice;
                dogs.add(dog);
                System.out.println(ConsoleColors.YELLOW + "done" + ConsoleColors.RESET);

            }
        } else
            System.out.println(ConsoleColors.RED + "this animal doesn't exist!" + ConsoleColors.RESET);

    }

    public void cage(int x, int y) {
        for (int i = 0; i < wilds.size(); i++) {
            WildAnimal w = wilds.get(i);
            if (w.getX() == x && w.getY() == y) {
                wilds.get(i).setHealth(w.getHealth() - 1);
                if (w.getHealth() <= -1) {
                    if (store.getRemaining() >= wilds.get(i).getCapacity()) {
                        int c = store.wildAnimalCap.get(w.getName());
                        store.wildAnimalCap.put(w.getName(), c + 1);
                        wilds.remove(i);
                    } else {
                        System.out.println(ConsoleColors.RED + "there isn't enough space to store animal!" + ConsoleColors.RESET);
                        w.setHealth(w.getHealth() + 1);
                    }
                } else if (w.getHealth() == 0)
                    wilds.get(i).setPrisonTime(time);
                return;
            }
        }
        System.out.println(ConsoleColors.RED + "there is not any animal here!" + ConsoleColors.RESET);
    }

    public void work(String factoryName) {
        for (Factory factory : factories) {
            if (factory.getName().equalsIgnoreCase(factoryName)) {
                if (factory.getTime() == -1) {
                    if (store.allProductsCap.get(factory.inProduct) > 0) {
                        factory.setTime(time);
                        int c = store.allProductsCap.get(factory.inProduct);
                        store.allProductsCap.put(factory.inProduct, c - 1);
                        return;
                    } else {
                        System.out.println(ConsoleColors.RED + factoryName + " need " + factory.inProduct + ConsoleColors.RESET);
                        return;
                    }
                } else {
                    System.out.println(ConsoleColors.RED + factoryName + " is working!" + ConsoleColors.RESET);
                    return;
                }
            }
        }
        System.out.println(ConsoleColors.RED + factoryName + " doesn't exist!" + ConsoleColors.RESET);
    }

    public void updateFactory(String name) {
        for (Factory f : factories) {
            if (f.getName().equalsIgnoreCase(name)) {
                if (coins >= f.getUpdateCoins()) {
                    f.update();
                    return;
                } else
                    System.out.println(ConsoleColors.RED + "you don't have enough money!" + ConsoleColors.RESET);
            }
        }
        System.out.println(ConsoleColors.RED + "factory " + name + " doesn't exist!" + ConsoleColors.RESET);
    }

    public void turn(int n) {
        boolean t1 = false, t2 = false, t3 = false;
        for (int i = 0; i < n; i++) {
            time++;
            if (time>6 && !t1){
                t1=true;
                wilds.add(new Lion());
            }
            else if (time>12 && !t2){
                t2=true;
                wilds.add(new Tiger());
            }
            else if (time>18 && !t3){
                t3=true;
                wilds.add(new Bear());
            }
            goTruckTurn();
            produce();
            destroyProducts();
            walk();
            eating();
            healthIncrease();

        }
        fillWell();
        workFactories();
        workCats();
        runWilds();
        show();

    }

    private void goTruckTurn() {
        if (truck.getGoTime() != -1 && time >= truck.getGoTime() + truck.getTimeInWay()) {
            truck.setGoTime(-1);
            Set s = truck.productInTruck.keySet();
            for (Object o : s) {
                int xo = truck.productInTruck.get(o);
                coins += findPrice(o.toString()) * xo;
                truck.productInTruck.put(o.toString(), 0);
            }
            truck.setTruckCapacity(truck.getMAX_Cap());
        }
    }

    private void healthIncrease() {
        for (WildAnimal wild : wilds) {
            if (wild.getHealth() > 0 && wild.getHealth() < wild.MAX_HEALTH)
                wild.setHealth(wild.getHealth() + 1);
        }
    }

    private void runWilds() {
        for (int i = 0; i < wilds.size(); i++) {
            if (wilds.get(i).getHealth() == 0 && time >= wilds.get(i).getPrisonTime() + 5) {
                wilds.remove(i);
            }
        }
    }

    private void fillWell() {
        if (well.getTime() != -1 && time - well.getTime() >= 3) {
            well.setRemaining(well.getCapacity());
            well.setTime(-1);
        }
    }

    private void walk() {
        for (Dog dog : dogs)
            walkH(dog);

        for (int i = 0; i < wilds.size(); i++) {
            if (wilds.get(i).getHealth() != 0)
                walkH(wilds.get(i));
            for (int j = 0; j < domestics.size(); j++) {
                if (wilds.get(i).health != 0 && wilds.get(i).getX() == domestics.get(j).getX() && wilds.get(i).getY() == domestics.get(j).getY())
                    domestics.remove(j);
            }

            for (int j = 0; j < cats.size(); j++) {
                if (wilds.get(i).health != 0 && wilds.get(i).getX() == cats.get(j).getX() && wilds.get(i).getY() == cats.get(j).getY())
                    cats.remove(j);
            }
            for (int j = 0; j < dogs.size(); j++) {
                if (wilds.get(i).health != 0 && wilds.get(i).getX() == dogs.get(j).getX() && wilds.get(i).getY() == dogs.get(j).getY()) {
                    wilds.remove(i);
                    dogs.remove(j);
                }
            }
        }
    }

    private void eating() {

        for (int i = 0; i < domestics.size(); i++) {
            DomesticAnimal d = domestics.get(i);
            d.setHealth(d.getHealth() - 0.5);
            if (d.getHealth() <= 0) {
                domestics.remove(i);
                System.out.println(ConsoleColors.YELLOW + d.getName() + " died! :((" + ConsoleColors.RESET);
            } else if (d.getHealth() <= 2.5) {
                eatingH(d);
                if (grasses[d.getY()][d.getX()] > 0) {
                    grasses[d.getY()][d.getX()]--;
                    d.setHealth(5);
                }
            } else
                walkH(d);
        }
    }

    private void eatingH(DomesticAnimal d) {
        int x = d.getX();
        int y = d.getY();
        int min_dis = 100;
        int xa = -1;
        int ya = -1;
        boolean find = false;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (grasses[j][i] > 0 && min_dis > distance(i, j, x, y)) {
                    find = true;
                    min_dis = distance(i, j, x, y);
                    xa = i;
                    ya = j;
                }
            }
        }

        if (find) {
            if (xa - x > 0) x++;
            else if (xa - x < 0) x--;
            if (ya - y > 0) y++;
            else if (ya - y < 0) y--;
            d.setX(x);
            d.setY(y);
        } else {
            System.out.println(ConsoleColors.RED + "aaaaaahhhhhhhh" + ConsoleColors.RESET);
        }
    }

    public int distance(int x, int y, int xa, int ya) {
        int delX = xa - x;
        int delY = ya - y;
        if (delX < 0) delX *= -1;
        if (delY < 0) delY *= -1;
        return Math.max(delX, delY);
    }

    private void walkH(DomesticAnimal d) {
        int x = d.getX();
        int y = d.getY();
        Random random = new Random();
        int delX = random.nextInt(3) - 1;
        int delY = random.nextInt(3) - 1;
        x += delX;
        y += delY;

        if (x > 5) x--;
        else if (x < 0) x++;

        if (y > 5) y--;
        else if (y < 0) y++;
        d.setX(x);
        d.setY(y);
    }

    private void walkH(WildAnimal d) {
        int x = d.getX();
        int y = d.getY();
        Random random = new Random();
        int delX, delY;
        if (d.getClass() == Tiger.class) {
            delX = random.nextInt(5) - 2;
            delY = random.nextInt(5) - 2;
        } else {
            delX = random.nextInt(3) - 1;
            delY = random.nextInt(3) - 1;
        }
        x += delX;
        y += delY;

        if (x == 6) x--;
        else if (x == 7) x -= 2;
        else if (x == -1) x++;
        else if (x == -2) x += 2;
        if (y == 6) y--;
        else if (y == 7) x -= 2;
        else if (y == -1) y++;
        else if (y == -2) y += 2;

        d.setX(x);
        d.setY(y);
    }

    private void walkH(Dog d) {
        int x = d.getX();
        int y = d.getY();
        Random random = new Random();
        int delX = random.nextInt(3) - 1;
        int delY = random.nextInt(3) - 1;
        x += delX;
        y += delY;
        if (x > 5) x--;
        else if (x < 0) x++;
        if (y > 5) y--;
        else if (y < 0) y++;
        d.setX(x);
        d.setY(y);
    }

    private void walkH(Cat d) {
        int x = d.getX();
        int y = d.getY();
        Random random = new Random();
        int delX = random.nextInt(3) - 1;
        int delY = random.nextInt(3) - 1;
        x += delX;
        y += delY;
        if (x > 5) x--;
        else if (x < 0) x++;
        if (y > 5) y--;
        else if (y < 0) y++;
        d.setX(x);
        d.setY(y);
    }

    private void workFactories() {
        for (Factory factory : factories) {
            if (factory.getTime() != -1 && time >= factory.time + factory.getTime_need_produce()) {
                Random random = new Random();
                int x = random.nextInt(5);
                int y = random.nextInt(5);
                Product product = new Product(factory.outProduct, x, y);
                products.add(product);
                factory.setTime(-1);
                break;
            }
        }
    }

    private void destroyProducts() {
        for (int i = 0; i < products.size(); i++) {
            if (time >= products.get(i).destroyTime() + products.get(i).getTime())
                products.remove(i);
        }
    }

    private void produce() {
        for (DomesticAnimal animal : domestics) {
            if (time >= animal.getTimeNeedToProduce() + animal.getLastTimeProduce()) {
                Product product = new Product(animal.productName, animal.getX(), animal.getY());
                products.add(product);
                animal.setLastTimeProduce(time);
            }
        }
    }

    private void workCats() {
        boolean haveSameProduct = false, getCat = false;
        ArrayList<Integer> saveIndex2 = new ArrayList<>();
//        int saveIndex=-1;
        ArrayList<Integer> saveIndex = new ArrayList<Integer>();
        for (Cat cat : cats) {
            int min_dis = 100;
            int xa = -1, ya = -1;
            for (int i = 0; i < products.size(); i++) {
                if (distance(cat.getX(), cat.getY(), products.get(i).getX(), products.get(i).getY()) < min_dis) {
                    min_dis = distance(cat.getX(), cat.getY(), products.get(i).getX(), products.get(i).getY());
                    ya = products.get(i).getY();
                    xa = products.get(i).getX();
                    saveIndex.clear();
                    saveIndex.add(i);
                } else if (distance(cat.getX(), cat.getY(), products.get(i).getX(), products.get(i).getY()) == min_dis) {
                    saveIndex.add(i);
                }
            }
            if (products.size() != 0) {
                if (xa - cat.getX() > 0) cat.setX(cat.getX() + 1);
                else if (xa - cat.getX() < 0) cat.setX(cat.getX() - 1);
                if (ya - cat.getY() > 0) cat.setY(cat.getY() + 1);
                else if (ya - cat.getY() < 0) cat.setY(cat.getY() - 1);
            }

            int t = saveIndex.size();
            for (int j = 0; j < t; j++) {
                if (products.get(saveIndex.get(j)).getX() == cat.getX() && products.get(saveIndex.get(j)).getY() == cat.getY()) {
                    if (store.getCapacity() >= 0) {
                        int temp = saveIndex.get(j);
                        int c = store.allProductsCap.get(products.get(saveIndex.get(j)).getName());
                        store.allProductsCap.put(products.get(saveIndex.get(j)).getName(), c + 1);
                        saveIndex2.add(temp);
                    } else {
                        System.out.println(ConsoleColors.RED+"store is full!!!"+ConsoleColors.RESET);
                    }
                }
            }
            int t3 = 0;
            if (saveIndex2.size() != 0)
                t3 = saveIndex2.get(0);
            for (int u = 0; u < saveIndex2.size(); u++) {
                products.remove(t3);
            }

            if (products.size() == 0) walkH(cat);
        }
    }

    public void show() {
        System.out.println("truck capacity: " + truck.getTruckCapacity());
        System.out.println("truck: " + truck.getProductInTruck().entrySet());
        System.out.println("wildAnimal in store: " + store.wildAnimalCap);
        System.out.println("products in store: " + store.allProductsCap.entrySet());
        System.out.print("time : ");
        System.out.println(time);
        System.out.print("coins : ");
        System.out.println(coins);
        for (Factory f : factories)
            System.out.println(f.toString());
        for (WildAnimal wild : wilds)
            wild.print();
        for (DomesticAnimal d : domestics)
            d.print();
        for (Cat cat : cats)
            System.out.println(cat.toString());
        for (Dog dog : dogs)
            System.out.println(dog.toString());
        for (Product product : products)
            System.out.println(product.toString());
        printGrass();
        System.out.println("store capacity : " + store.getRemaining());


    }

    public void truckLoad(String name, int amount) {
        if (truck.getGoTime() == -1) {
            if (store.allProductsCap.get(name) != null) {
                if (store.allProductsCap.get(name) >= amount && truck.getTruckCapacity() > 0) {
                    truck.setTruckCapacity(truck.getTruckCapacity() - (amount * Product.findVolume(name)));
                    if (truck.getTruckCapacity() >= 0) {
                        store.allProductsCap.replace(name, store.allProductsCap.get(name) - amount);
                        truck.getProductInTruck().replace(name, amount + truck.getProductInTruck().get(name));
                        System.out.println("the truck load is done!!");
                    } else {
                        truck.setTruckCapacity(truck.getTruckCapacity() + (amount * Product.findVolume(name)));
                        System.out.println("here is not enough space in truck!!!");
                    }
                } else if (store.allProductsCap.get(name) < amount) {
                    System.out.println(ConsoleColors.RED + "there is not enough product in the store!!" + ConsoleColors.RESET);
                } else if (truck.getTruckCapacity() <= 0) {
                    System.out.println(ConsoleColors.RED+"there is not enough space in truck!!"+ConsoleColors.RESET);
                }
            } else if (store.wildAnimalCap.get(name) != null) {
                if (store.wildAnimalCap.get(name) >= amount && truck.getTruckCapacity() > 0) {
                    truck.setTruckCapacity(truck.getTruckCapacity() - (amount * 15));
                    if (truck.getTruckCapacity() >= 0) {
                        store.wildAnimalCap.replace(name, store.wildAnimalCap.get(name) - amount);
                        truck.getProductInTruck().replace(name, amount + truck.getProductInTruck().get(name));
                        System.out.println("the truck load is done!!");
                    } else {
                        truck.setTruckCapacity(truck.getTruckCapacity() + (amount * 15));
                        System.out.println(ConsoleColors.RED+"there isnt enough space in truck!!!"+ConsoleColors.RESET);
                    }
                } else if (store.wildAnimalCap.get(name) < amount) {
                    System.out.println(ConsoleColors.RED + "there is not enough product in the store!!" + ConsoleColors.RESET);
                } else if (truck.getTruckCapacity() <= 0) {
                    System.out.println(ConsoleColors.RED+"there is not enough space in truck!!!"+ConsoleColors.RESET);
                }
            } else {
                System.out.println("the product name is unavailable!!!");
            }
        } else
            System.out.println(ConsoleColors.RED + "truck is on way!" + ConsoleColors.RESET);
    }

    public void truckUnload(String name, int amount) {
        if (truck.getGoTime() == -1) {
            if (truck.getProductInTruck().get(name) != null) {
                if (store.wildAnimalCap.get(name) != null) {
                    if (truck.getProductInTruck().get(name) >= amount) {
                        truck.setTruckCapacity(truck.getTruckCapacity() + (amount * 15));
                        truck.getProductInTruck().replace(name, truck.getProductInTruck().get(name) - amount);
                        store.wildAnimalCap.replace(name, amount + store.wildAnimalCap.get(name));
                        System.out.println("the order has done!!!");
                    } else if (truck.getProductInTruck().get(name) < amount) {
                        System.out.println(ConsoleColors.RED + "there is not enough product in truck!!!" + ConsoleColors.RESET);
                    } else {
                        System.out.println("there is not product in the truck!!!");
                    }
                } else if (store.allProductsCap.get(name) != null) {
                    if (truck.getProductInTruck().get(name) >= amount) {
                        truck.setTruckCapacity(truck.getTruckCapacity() + (amount * Product.findVolume(name)));
                        truck.getProductInTruck().replace(name, truck.getProductInTruck().get(name) - amount);
                        store.allProductsCap.replace(name, amount + store.allProductsCap.get(name));
                        System.out.println("the order has done!!!");
                    } else if (truck.getProductInTruck().get(name) < amount) {
                        System.out.println(ConsoleColors.RED + "there is not enough product in truck!!!" + ConsoleColors.RESET);
                    } else {
                        System.out.println("there is not product in the truck!!!");
                    }
                }
            } else {
                System.out.println(ConsoleColors.RED+"there is not enough thing in the truck!!!"+ConsoleColors.RESET);
            }
        } else
            System.out.println(ConsoleColors.RED + "truck is on way!" + ConsoleColors.RESET);
    }

    public void truckGo() {
        if (truck.getGoTime() == -1) {
            if (truck.getTruckCapacity() != truck.getMAX_Cap()) {
                truck.setGoTime(time);
            } else
                System.out.println(ConsoleColors.RED + "truck is empty!" + ConsoleColors.RESET);
        } else
            System.out.println(ConsoleColors.RED + "truck is on way" + ConsoleColors.RESET);
    }

    public void newFactory(String name) {
        if (factories.size() >= 6)
            System.out.println(ConsoleColors.RED + "no space to new factory!" + ConsoleColors.RESET);
        else {
            if (name.equalsIgnoreCase("Mill")) {
                Mill mill = new Mill();
                if (coins >= mill.price) {
                    factories.add(mill);
                    coins -= mill.price;
                } else
                    System.out.println("you don't have money!");

            } else if (name.equalsIgnoreCase("Bakery")) {
                Bakery bakery = new Bakery();
                if (coins >= bakery.price) {
                    factories.add(bakery);
                    coins -= bakery.price;
                } else
                    System.out.println("you don't have money!");

            } else if (name.equalsIgnoreCase("iceCreamMaker")) {
                IceCreamMaker ice = new IceCreamMaker();
                if (coins >= ice.price) {
                    factories.add(ice);
                    coins -= ice.price;
                } else
                    System.out.println("you don't have money!");

            } else if (name.equalsIgnoreCase("Loom")) {
                Loom loom = new Loom();
                if (coins >= loom.price) {
                    factories.add(loom);
                    coins -= loom.price;
                } else
                    System.out.println("you don't have money!");

            } else if (name.equalsIgnoreCase("MilkPack")) {
                MilkPack mill = new MilkPack();
                if (coins >= mill.price) {
                    factories.add(mill);
                    coins -= mill.price;
                } else
                    System.out.println("you don't have money!");
            } else if (name.equalsIgnoreCase("tailoring")) {
                Tailoring tailoring = new Tailoring();
                if (coins >= tailoring.price) {
                    factories.add(tailoring);
                    coins -= tailoring.price;
                } else
                    System.out.println("you don't have money!");
            } else
                System.out.println(ConsoleColors.RED + "factory doesn't exist!" + ConsoleColors.RESET);
        }
    }

     private void printGrass(){
         for (int i = 0; i < 6; i++) {
             for (int j = 0; j < 6; j++) {
                 System.out.print(grasses[j][i]+"\t");
             }
             System.out.println();
         }
     }
}
