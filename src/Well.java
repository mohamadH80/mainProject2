public class Well {

    private int level;
    private int remaining;
    private int capacity;
    private final int MAX_LEVEL=5;
    private int time;

    public Well() {
        level = 1;
        capacity = 5;
        remaining = capacity;
        time=-1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getCapacity() {
        return capacity;
    }

    public void upgrade() {
        if (Input.manager.getCoins() < 100) {
            System.out.println(ConsoleColors.RED+"you don't have enough money!"+ConsoleColors.RESET);
            return;
        }

        if (level>=MAX_LEVEL)
            return;

        Input.manager.setCoins(Input.manager.getCoins()-100);
        level++;
        capacity++;
    }


}
