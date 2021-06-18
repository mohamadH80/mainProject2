public class IceCreamMaker extends Factory{


    public IceCreamMaker() {
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
