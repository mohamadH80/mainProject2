public class Hen extends DomesticAnimal {


    public Hen() {
        super(5, 100,50,3,"egg");

    }

    @Override
    public int getTimeNeedToProduce() {
        return 2;
    }


}
