public class User {
    private short[] levels;
    private String username;
    private String pass;

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
        levels = new short[5];
        levels[0]=1;
    }

    public int lastOpenLevel() {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i] == 0)
                return i + 1;
        }
        return 1;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPassTrue(String pass){
        if (pass.equals(this.pass))
            return true;
        return false;
    }



}
