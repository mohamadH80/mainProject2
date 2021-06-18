public class MilkPack extends Factory {

    public MilkPack() {
        super("milk", "pocketMilk",400);
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
