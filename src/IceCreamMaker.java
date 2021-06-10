public class IceCreamMaker extends Factory{


    public IceCreamMaker(String inProduct, String outProduct) {
        super("pocketMilk", "iceCream");
    }

    @Override
    public String getName() {
        return "IceCreamMaker";
    }

    @Override
    public int getTimeToProduce() {
        return 7;
    }
}
