public class Mill extends Factory{

    public Mill() {
        super("egg", "flour",150);
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
