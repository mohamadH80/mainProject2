public class MilkPack extends Factory {

    public MilkPack() {
        super("milk", "pocketMilk");
    }

    @Override
    public String getName() {
        return "MilkPack";
    }

    @Override
    public int getTimeToProduce() {
        return 6;
    }
}
