public class Buffalo extends DomesticAnimal{



    public Buffalo() {
        super(5,400,200,10,"milk");
    }

    @Override
    public int getTimeNeedToProduce() {
        return 5;
    }
}
