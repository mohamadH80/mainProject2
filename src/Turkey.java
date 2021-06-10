public class Turkey extends DomesticAnimal{
    public Turkey() {
        super(5, 200,100,6,"feather");
    }

    @Override
    public int getTimeNeedToProduce() {
        return 3;
    }
}
