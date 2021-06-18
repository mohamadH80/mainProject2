import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

//todo truck and updates remain
public class Manager {

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

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
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
                if (w.getHealth() <= 0) {
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
            if (factory.getName().equals(factoryName)) {
                if (factory.getTime() == -1) {
                    if (store.allProductsCap.get(factory.inProduct) > 0) {
                        factory.setTime(time);
                        int c = store.allProductsCap.get(factory.inProduct);
                        store.allProductsCap.put(factory.inProduct, c - 1);
                        return;
                    } else {
                        System.out.println(ConsoleColors.RED + factoryName + " need " + factory.inProduct);
                        return;
                    }
                } else {
                    System.out.println(ConsoleColors.RED + factoryName + " is working!" + ConsoleColors.RESET);
                    return;
                }
            }
        }
        System.out.println("This factory isnt build do you want to build!?");
        System.out.println("1 : yes  ,  2 : no");
        String choise=Input.scanner.nextLine();
        if (choise.equals("1"))
        {
            if (factoryName.equals("Bakery"))
            {
                coins-=150;
                Factory tempFactory=new Bakery();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else if (factoryName.equals("IceCreamMaker"))
            {
                coins-=150;
                Factory tempFactory=new IceCreamMaker();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else if (factoryName.equals("Loom"))
            {
                coins-=150;
                Factory tempFactory=new Loom();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else if (factoryName.equals("MilkPack"))
            {
                coins-=150;
                Factory tempFactory=new MilkPack();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else if (factoryName.equals("Mill"))
            {
                coins-=150;
                Factory tempFactory=new Mill();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else if (factoryName.equals("Tailoring"))
            {
                coins-=150;
                Factory tempFactory=new Tailoring();
                factories.add(tempFactory);
                System.out.println("Factory added!!");
            }
            else
            {
                System.err.println("there is not factory with this name!!!");
                System.out.println(ConsoleColors.RED + factoryName + " not added!"+ConsoleColors.RESET);
            }
        }
    }

    public void turn(int n) {
        wilds.add(new Bear());

        for (int i = 0; i < n; i++) {
            time++;
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

//        System.out.println(wilds.size());

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
                if (wilds.get(i).health != 0 && wilds.get(i).getX() == dogs.get(j).getX() && wilds.get(i).getY() == dogs.get(j).getY())
                {
                    wilds.remove(i);
                    dogs.remove(j);
                    System.out.println("DAfsdgfd");
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
            if (factory.getTime() != -1 && time >= factory.time + factory.getTimeToProduce()) {
                Random random = new Random();
                int x = random.nextInt(5);
                int y = random.nextInt(5);
                Product product = new Product(factory.outProduct, x, y);
                products.add(product);
                factory.setTime(-1);
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

            for (int j = 0; j < saveIndex.size(); j++)
            {
                if (products.get(saveIndex.get(j)).getX() == cat.getX() && products.get(saveIndex.get(j)).getY() == cat.getY())
                {
                    if (store.getCapacity()>=0)
                    {
                        int temp=saveIndex.get(j);
                        int c = store.allProductsCap.get(products.get(saveIndex.get(j)).getName());
                        store.allProductsCap.put(products.get(saveIndex.get(j)).getName(), c + 1);
                        products.remove(temp);
                        saveIndex.remove(j);
                    }
                    else
                    {
                        System.err.println("store is full!!!");
                    }
                }
            }

            if (products.size() == 0) walkH(cat);
        }
    }

    public void show() {
        System.out.println("truck capacity: "+truck.getTruckCapacity());
        System.out.println("truck: "+truck.getProductInTruck().entrySet());
        System.out.println("wildAnimal in store: "+store.wildAnimalCap);
        System.out.println("products in store: "+store.allProductsCap.entrySet());
        System.out.print("time : ");
        System.out.println(time);
        System.out.print("coins : ");
        System.out.println(coins);
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
        System.out.println("store capacity : "+store.getRemaining());

    }

    public void truckLoad(String name, int amount)
    {
        if (store.allProductsCap.get(name)!=null)
        {
            if (store.allProductsCap.get(name)>=amount && truck.getTruckCapacity()>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()-(amount*Product.findVolume(name)));
                if (truck.getTruckCapacity()>=0)
                {
                    store.allProductsCap.replace(name,store.allProductsCap.get(name)-amount);
                    truck.getProductInTruck().replace(name,amount+truck.getProductInTruck().get(name));
                    System.out.println("the truck load is done!!");
                }
                else
                {
                    truck.setTruckCapacity(truck.getTruckCapacity()+(amount*Product.findVolume(name)));
                    System.out.println("here is not enough space in truck!!!");
                }
            }
            else if (store.allProductsCap.get(name)<amount)
            {
                System.out.println(ConsoleColors.RED+"there is not enough product in the store!!"+ConsoleColors.RESET);
            }
            else if (truck.getTruckCapacity()<=0)
            {
                System.err.println("there is not enough space in truck!!!");
            }
        }
        else if (store.wildAnimalCap.get(name)!=null )
        {
            if (store.wildAnimalCap.get(name)>=amount && truck.getTruckCapacity()>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()-(amount*15));
                if (truck.getTruckCapacity()>=0)
                {
                    store.wildAnimalCap.replace(name,store.wildAnimalCap.get(name)-amount);
                    truck.getProductInTruck().replace(name,amount+truck.getProductInTruck().get(name));
                    System.out.println("the truck load is done!!");
                }
                else
                {
                    truck.setTruckCapacity(truck.getTruckCapacity()+(amount*15));
                    System.err.println("there isnt enough space in truck!!!");
                }
            }
            else if (store.wildAnimalCap.get(name)<amount)
            {
                System.out.println(ConsoleColors.RED+"there is not enough product in the store!!"+ConsoleColors.RESET);
            }
            else if (truck.getTruckCapacity()<=0)
            {
                System.err.println("there is not enough space in truck!!!");
            }
        }
        else
        {
            System.out.println("the product name is unavailable!!!");
        }
    }

    public void truckUnload(String name, int amount)
    {
        if (truck.getProductInTruck().get(name)!=null)
        {
            if (store.wildAnimalCap.get(name)!=null)
            {
                if (truck.getProductInTruck().get(name)>=amount)
                {
                    truck.setTruckCapacity(truck.getTruckCapacity()+(amount*15));
                    truck.getProductInTruck().replace(name,truck.getProductInTruck().get(name)-amount);
                    store.wildAnimalCap.replace(name,amount+store.wildAnimalCap.get(name));
                    System.out.println("the order has done!!!");
                }
                else if (truck.getProductInTruck().get(name)<amount)
                {
                    System.out.println(ConsoleColors.RED+"there is not enough product in truck!!!"+ConsoleColors.RESET);
                }
                else
                {
                    System.out.println("there is not product in the truck!!!");
                }
            }
            else if (store.allProductsCap.get(name)!=null)
            {
                if (truck.getProductInTruck().get(name)>=amount)
                {
                    truck.setTruckCapacity(truck.getTruckCapacity()+(amount*Product.findVolume(name)));
                    truck.getProductInTruck().replace(name,truck.getProductInTruck().get(name)-amount);
                    store.allProductsCap.replace(name,amount+store.allProductsCap.get(name));
                    System.out.println("the order has done!!!");
                }
                else if (truck.getProductInTruck().get(name)<amount)
                {
                    System.out.println(ConsoleColors.RED+"there is not enough product in truck!!!"+ConsoleColors.RESET);
                }
                else
                {
                    System.out.println("there is not product in the truck!!!");
                }
            }
        }
        else
        {
            System.err.println("there is not enough thing in the truck!!!");
        }
    }

    public void truckGo()
    {
        if (truck.getTruckCapacity()==15)
        {
            System.out.println("truck is empty!!");
        }
        else
        {
            System.out.println("Done!!");//hgvv
        }
        for (int i=0;i<truck.productInTruck.size();i++)
        {
            if (truck.getProductInTruck().get("egg")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("egg")*Product.findVolume("egg")));
                coins+=truck.getProductInTruck().get("egg")*Product.findPrice("egg");
                truck.getProductInTruck().replace("egg",0);
            }
            else if (truck.getProductInTruck().get("feather")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("feather")*Product.findVolume("feather")));
                coins+=truck.getProductInTruck().get("feather")*Product.findPrice("feather");
                truck.getProductInTruck().replace("feather",0);
            }
            else if (truck.getProductInTruck().get("milk")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("milk")*Product.findVolume("milk")));
                coins+=truck.getProductInTruck().get("milk")*Product.findPrice("milk");
                truck.getProductInTruck().replace("milk",0);
            }
            else if (truck.getProductInTruck().get("flour")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("flour")*Product.findVolume("flour")));
                coins+=truck.getProductInTruck().get("flour")*Product.findPrice("flour");
                truck.getProductInTruck().replace("flour",0);
            }
            else if (truck.getProductInTruck().get("cloth")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("cloth")*Product.findVolume("cloth")));
                coins+=truck.getProductInTruck().get("cloth")*Product.findPrice("cloth");
                truck.getProductInTruck().replace("cloth",0);
            }
            else if (truck.getProductInTruck().get("pocketMilk")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("pocketMilk")*Product.findVolume("pocketMilk")));
                coins+=truck.getProductInTruck().get("pocketMilk")*Product.findPrice("pocketMilk");
                truck.getProductInTruck().replace("pocketMilk",0);
            }
            else if (truck.getProductInTruck().get("bread")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("bread")*Product.findVolume("bread")));
                coins+=truck.getProductInTruck().get("bread")*Product.findPrice("bread");
                truck.getProductInTruck().replace("bread",0);
            }
            else if (truck.getProductInTruck().get("shirt")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("shirt")*Product.findVolume("shirt")));
                coins+=truck.getProductInTruck().get("shirt")*Product.findPrice("shirt");
                truck.getProductInTruck().replace("shirt",0);
            }
            else if (truck.getProductInTruck().get("iceCream")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("iceCream")*Product.findVolume("iceCream")));
                coins+=truck.getProductInTruck().get("iceCream")*Product.findPrice("iceCream");
                truck.getProductInTruck().replace("iceCream",0);
            }
            else if (truck.getProductInTruck().get("Bear")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("Bear")*15));
                coins+=truck.getProductInTruck().get("Bear")*400;
                truck.getProductInTruck().replace("Bear",0);
            }
            else if (truck.getProductInTruck().get("Lion")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("Lion")*15));
                coins+=truck.getProductInTruck().get("Lion")*300;
                truck.getProductInTruck().replace("Lion",0);
            }
            else if (truck.getProductInTruck().get("Tiger")>0)
            {
                truck.setTruckCapacity(truck.getTruckCapacity()+(truck.getProductInTruck().get("Tiger")*15));
                coins+=truck.getProductInTruck().get("Tiger")*500;
                truck.getProductInTruck().replace("Tiger",0);
            }
        }
    }
}
