public abstract class Factory {
    //todo ez time
    protected String inProduct;
    protected String outProduct;
    protected int factoryLevel;
    protected int time;
    public Factory(String inProduct, String outProduct) {
        this.inProduct = inProduct;
        this.outProduct = outProduct;
        time=-1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    abstract public String getName();

    abstract public int getTimeToProduce();


}
