public class Lion extends WildAnimal{

    public Lion() {
        super(3, 150);
    }


    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Lion{" +
                "health=" + health +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
