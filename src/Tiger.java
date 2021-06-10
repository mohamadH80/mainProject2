public class Tiger extends WildAnimal{

    public Tiger() {
        super(4, 250);
    }


    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "health=" + health +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
