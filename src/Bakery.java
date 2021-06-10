public class Bakery extends Factory{

    public Bakery(String inProduct, String outProduct) {
        super("flour", "bread");
    }

    @Override
    public String getName() {
        return "Bakery";
    }

    @Override
    public int getTimeToProduce() {
        return 5;
    }
}
