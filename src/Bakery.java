public class Bakery extends Factory{

    public Bakery() {
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
