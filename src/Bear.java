public class Bear extends WildAnimal{

    public Bear() {
        super(4, 200);
    }


    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Bear{" +
                "health=" + health +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
