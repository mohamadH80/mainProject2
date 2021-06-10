public class Tailoring extends Factory {

    public Tailoring() {
        super("cloth", "shirt");
    }

    @Override
    public String getName() {
        return "Tailoring";
    }

    @Override
    public int getTimeToProduce() {
        return 6;
    }
    //khayati
}
