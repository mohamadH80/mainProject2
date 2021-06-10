public class Loom extends Factory {

    public Loom() {
        super("feather", "cloth");
    }

    @Override
    public String getName() {
        return "Loom";
    }

    @Override
    public int getTimeToProduce() {
        return 5;
    }
    //parche baf

}
