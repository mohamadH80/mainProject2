public class Loom extends Factory {

    public Loom() {
        super("feather", "cloth",250);
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
