public abstract class Factory {
    //todo ez time
    protected String inProduct;
    protected String outProduct;
    protected int factoryLevel;
    protected int time;
    protected int price;
    private int updateCoins;
    private int time_need_produce;

    public Factory(String inProduct, String outProduct, int price, int time) {
        this.inProduct = inProduct;
        this.outProduct = outProduct;
        time = -1;
        factoryLevel = 1;
        this.price = price;
        updateCoins = price * 2 / 3;
        time_need_produce = time;
    }

    public int getTime_need_produce() {
        return time_need_produce;
    }

    public int getUpdateCoins() {
        return updateCoins;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    abstract public String getName();

    public void update() {
        time_need_produce /= 2;
        factoryLevel++;
        Input.manager.setCoins(Input.manager.getCoins() - updateCoins);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "name=" + getName() +
                ", factoryLevel=" + factoryLevel +
                '}';
    }

}
