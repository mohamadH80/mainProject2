public abstract class Factory {
    //todo ez time
    protected String inProduct;
    protected String outProduct;
    protected int factoryLevel;
    protected int time;
    protected int price;

    public Factory(String inProduct, String outProduct, int price) {
        this.inProduct = inProduct;
        this.outProduct = outProduct;
        time = -1;
        factoryLevel = 1;
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    abstract public String getName();

    abstract public int getTimeToProduce();

    @Override
    public String toString() {
        return "Factory{" +
                "name=" + getName() +
                "factoryLevel=" + factoryLevel +
                ", time=" + time +
                '}';
    }
}
