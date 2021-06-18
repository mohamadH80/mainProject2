public class IceCreamMaker extends Factory{


    public IceCreamMaker() {
        super("pocketMilk", "iceCream",550);
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
