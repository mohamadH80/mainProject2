public class Mill extends Factory{

    public Mill() {
        super("egg", "flour");
    }

    @Override
    public String getName() {
        return "Mill";
    }

    @Override
    public int getTimeToProduce() {
        return 4;
    }
    //asiab

}
