import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class Input {
    static Manager manager = new Manager();
    static Scanner scanner = new Scanner(System.in);
    String nowUserName = "";
    FileWriter fileWriter;
    FileReader fileReader;
    File file = new File("property");
    Gson gson = new GsonBuilder().create();
    JsonParser parser = new JsonParser();

    private void buy(String input) {
        manager.buy(input);
    }

    private void pickup(String[] input) {
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);
        if (x < 0 || y < 0 || x > 5 || y > 5) {
            System.out.print(ConsoleColors.RED + "out of range!" + ConsoleColors.RESET);
        } else
            manager.pickup(x, y);
    }

    private void well() {
        manager.well();
    }

    private void plant(String[] input) {
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);
        if (x < 0 || y < 0 || x > 5 || y > 5) {
            System.out.print(ConsoleColors.RED + "out of range!" + ConsoleColors.RESET);
        } else
            manager.plant(x, y);
    }

    private void cage(String[] input) {
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);
        if (x > 5 || x < 0 || y > 5 || y < 0)
            System.out.println(ConsoleColors.RED + "out of range!" + ConsoleColors.RESET);
        else
            manager.cage(x, y);

    }

    private void work(String[] input) {
        //todo ez
        manager.work(input[1]);
    }

    private void turn(String[] input) {
        //todo ez
        int n = Integer.parseInt(input[1]);
        if (n < 0)
            System.out.println(ConsoleColors.RED + "smart :/" + ConsoleColors.RESET);
        else
            manager.turn(n);
    }

    public void run(int level) {
        String order;
        boolean finish = false;
        System.out.println(ConsoleColors.YELLOW + "GAME START!" + ConsoleColors.RESET);
        checkStart(level);
        while (!finish) {
            System.out.print("enter command : ");
            order = scanner.nextLine();
            if (Pattern.compile("[bB][uU][yY] \\w+").matcher(order).find()) {
                buy(order.split("\\s")[1]);
            } else if (Pattern.compile("[pP][iI][cC][kK][uU][pP] \\d \\d").matcher(order).find()) {
                pickup(order.split("\\s"));
            } else if (order.equalsIgnoreCase("well")) {
                well();
            } else if (Pattern.compile("[pP][lL][aA][nN][eE][tT] \\d \\d").matcher(order).find()) {
                plant(order.split("\\s"));
            } else if (Pattern.compile("[wW][oO][rR][kK] \\w+").matcher(order).find()) {
                work(order.split("\\s"));
            } else if (Pattern.compile("[cC][aA][gG][eE] \\d \\d").matcher(order).find()) {
                cage(order.split("\\s"));
            } else if (Pattern.compile("[tT][uU][rR][Nn] \\d+").matcher(order).find()) {
                turn(order.split("\\s"));
            } else if (Pattern.compile("[tT][rR][uU][cC][Kk] [lL][oO][aA][Dd] \\w+").matcher(order).find()) {
                truckLoad(order.split("\\s"));
            } else if (Pattern.compile("[tT][rR][uU][cC][kK] [uU][nN][lL][oO][aA][dD] \\w+").matcher(order).find()) {
                truckUnload(order.split("\\s"));
            } else if (Pattern.compile("[tT][rR][uU][cC][kK] [Gg][Oo]").matcher(order).find()) {
                truckGo();
            } else if (Pattern.compile("[iI][nN][qQ][uU][iI][rR][Yy]").matcher(order).find()) {
                manager.show();
            } else if (Pattern.compile("[nN][eE][wW] [fF][aA][cC][tT][oO][rR][yY] \\w+").matcher(order).find()) {
                manager.newFactory(order.split("\\s")[2]);
            } else if (Pattern.compile("[uU][pP][dD][aA][tT][eE] [fF][aA][cC][tT][oO][rR][yY] \\w+").matcher(order).find()) {
                manager.updateFactory(order.split("\\s")[2]);
            } else if (Pattern.compile("[tT][aA][sS][kK]").matcher(order).find()) {
                task(level);
            } else {
                System.out.println(ConsoleColors.RED + "wrong command!" + ConsoleColors.RESET);
            }
            Manager.logger.log(Level.INFO, "sssssss");
            finish = checkEnd(level);
        }
        System.out.println("congratulation!\tyou win level " + level + "");
        menu();
    }

    private void task(int level) {
        switch (level){
            case 1:
                System.out.println(ConsoleColors.WHITE+"6 egg"+ConsoleColors.RESET);
                break;
            case 2:
                System.out.println(ConsoleColors.WHITE+"2 egg,2 hen"+ConsoleColors.RESET);
                break;
            case 3:
                System.out.println(ConsoleColors.WHITE+"2 flour,300 coins"+ConsoleColors.RESET);
                break;
            case 4:
                System.out.println(ConsoleColors.WHITE+"6 flour,5 hen,500 coins"+ConsoleColors.RESET);
                break;
            case 5:
                System.out.println(ConsoleColors.WHITE+"9 egg,5 flour,1 bread"+ConsoleColors.RESET);
                break;
        }
    }

    private boolean checkEnd(int level) {
        switch (level) {
            case 1:
                return e1();
            case 2:
                return e2();
            case 3:
                return e3();
            case 4:
                return e4();
            case 5:
                return e5();
        }
        return true;
    }

    private void checkStart(int level) {
        switch (level) {
            case 1:
                s1();
                break;
            case 2:
                s2();
                break;
            case 3:
                s3();
                break;
            case 4:
                s4();
                break;
            case 5:
                s5();
                break;
        }
    }

    private boolean e1() {
        if (manager.store.allProductsCap.get("egg") == 6) {
            short score = 0;
            if (manager.getTime() == 2)
                score = 3;
            else if (manager.getTime() == 4)
                score = 2;
            else
                score = 1;
            newLevel(manager);
            fileChange(fileCopy(file), 2, nowUserName);
            return true;
        }
        return false;
    }

    private boolean e2() {
        int hens = 0;
        for (DomesticAnimal d : manager.domestics) {
            if (d.getName().equalsIgnoreCase("hen"))
                hens++;
        }
        System.out.println("hen = " + hens);
        System.out.println("egg = " + manager.store.allProductsCap.get("egg"));
        if (manager.store.allProductsCap.get("egg") >= 2 && hens >= 2) {
            newLevel(manager);
            fileChange(fileCopy(file), 3, nowUserName);
            return true;
        }
        return false;
    }

    private boolean e3() {
        if (manager.store.allProductsCap.get("flour") == 2 && manager.getCoins() == 300) {
            fileChange(fileCopy(file), 4, nowUserName);
            newLevel(manager);
            return true;
        }
        return false;
    }

    private boolean e4() {
        int hens = 0;
        for (DomesticAnimal d : manager.domestics) {
            if (d.getName().equalsIgnoreCase("hen"))
                hens++;
        }
        if (manager.store.allProductsCap.get("flour") == 6 && manager.getCoins() == 500 && hens == 5) {
            fileChange(fileCopy(file), 5, nowUserName);
            newLevel(manager);
            return true;
        }
        return false;
    }

    private boolean e5() {
        if (manager.store.allProductsCap.get("egg") == 9 && manager.store.allProductsCap.get("flour") == 5 && manager.store.allProductsCap.get("bread") == 1) {
            newLevel(manager);
            return true;
        }
        return false;

    }

    private void s1() {
        manager.setCoins(500);
        manager.domestics.add(new Hen());
    }

    private void s2() {
        manager.setCoins(90);
        manager.wilds.add(new Bear());
        manager.wilds.add(new Bear());
    }

    private void s3() {
        manager.setCoins(100);
        manager.wilds.add(new Bear());
//        manager.wilds.add(new Bear());
    }

    private void s4() {
        manager.setCoins(400);
        manager.dogs.add(new Dog());
        manager.factories.add(new Mill());
    }

    private void s5() {
        manager.setCoins(0);
        manager.domestics.add(new Hen());
        manager.domestics.add(new Hen());
        manager.domestics.add(new Hen());
        manager.domestics.add(new Hen());
        manager.factories.add(new Mill());
    }

    private void newLevel(Manager manager) {
        manager.wilds.clear();
        manager.factories.clear();
        manager.store = null;
        manager.products.clear();
        manager.well = null;
        manager.cats.clear();
        manager.dogs.clear();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                manager.grasses[i][j] = 0;
        manager.setCoins(0);
        manager.setTime(0);
    }

    private void truckUnload(String[] input) {
        String name = input[2];
        int am = 0;
        System.out.println("enter your amount");
        am = scanner.nextInt();
        manager.truckUnload(name, am);
    }

    public void menu() {
        //todo(delete user)
        System.out.println("LOG IN(1)\tSIGNUP(2)");
        String state = scanner.nextLine();

        if (state.equals("1"))
            login();
        else if (state.equals("2"))
            signup();
        else {
            System.out.println(ConsoleColors.RED + "wrong :||" + ConsoleColors.RESET);
            menu();
        }
    }

    public void signup() {
        String pass;
        System.out.print("enter username : ");
        String name = scanner.nextLine();
        try {
            if (!file.exists())
                file.createNewFile();
            String line = "";
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                Object object = parser.parse(line);
                JsonObject jsonObject = (JsonObject) object;
                String username = jsonObject.get("username").toString();
                if (username.substring(1, username.length() - 1).equals(name)) {
                    System.out.println(ConsoleColors.RED + "the username have already exist you can login!!!" + ConsoleColors.RESET);
                    bufferedReader.close();
                    menu();
                    return;
                }
            }
            System.out.println(ConsoleColors.BLUE + "please Enter your password!!!");
            pass = scanner.nextLine();
            try {
                fileWriter = new FileWriter("property", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                if (file.length() != 0)
                    bufferedWriter.newLine();

                User user = new User(name, pass);
                gson.toJson(user, bufferedWriter);
                bufferedWriter.close();
                System.out.println(ConsoleColors.CYAN + "sign up successfully done :)" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.PURPLE + "Enter the game with choose the login :)" + ConsoleColors.RESET);
                menu();
            } catch (IOException exception) {
                System.out.println(ConsoleColors.RED + "Error in opening account!!!" + ConsoleColors.RESET);
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "error in signing up!!!" + ConsoleColors.RESET);
        }
    }

    public void login() {
        boolean haveUser = false;
        String pass;
        System.out.print("enter username : ");
        String name = scanner.nextLine();
        try {
            String line;
            fileReader = new FileReader("property");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                Object object = parser.parse(line);
                JsonObject jsonObject = (JsonObject) object;
                String username = jsonObject.get("username").toString();
                if (username.substring(1, username.length() - 1).equals(name)) {
                    haveUser = true;
                    System.out.println(ConsoleColors.RED + "Enter your password!!!" + ConsoleColors.RESET);
                    bufferedReader.close();
                    pass = scanner.nextLine();
                    String passCertificate = jsonObject.get("pass").toString();
                    if (passCertificate.substring(1, passCertificate.length() - 1).equals(pass)) {
                        System.out.println(ConsoleColors.GREEN + "Welcome :) !!!" + ConsoleColors.RESET);
                        nowUserName = name;
                        startMenu(username.substring(1, username.length() - 1));
                    } else {
                        System.out.println(ConsoleColors.RED + "your pass incorrect ReEnter your pass you can write (back) to go to menu:||" + ConsoleColors.RESET);
                        pass = scanner.nextLine();
                        while (!passCertificate.substring(1, passCertificate.length() - 1).equals(pass) && !pass.equals("back")) {
                            System.out.println(ConsoleColors.RED + "your pass word is wrong you can write (back) to go to menu :||" + ConsoleColors.RESET);
                            pass = scanner.nextLine();
                            if (pass.equals("back")) {
                                menu();
                                return;
                            }
                        }
                        startMenu(username.substring(1, username.length() - 1));
                        return;
                    }
                }
            }
        } catch (IOException exception) {

        }
        if (!haveUser) {
            System.out.println(ConsoleColors.RED + "there is no user with this username!!!!" + ConsoleColors.RESET);
            menu();
        }
    }

    public boolean isNumberic(String s) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (s == null) {
            return false;
        }
        return pattern.matcher(s).matches();
    }

    public void startMenu(String name) {
        String templevels;
        String[] levels = new String[5];
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                Object object = parser.parse(line);
                JsonObject jsonObject = (JsonObject) object;
                if (jsonObject.get("username").toString().substring(1, jsonObject.get("username").toString().length() - 1).equals(name)) {
                    templevels = jsonObject.get("levels").toString();
                    levels[0] = templevels.substring(1, 2);
                    levels[1] = templevels.substring(3, 4);
                    levels[2] = templevels.substring(5, 6);
                    levels[3] = templevels.substring(7, 8);
                    levels[4] = templevels.substring(9, 10);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        String level;
        String choose;
        System.out.println(ConsoleColors.GREEN + "Enter your order :)" + ConsoleColors.RESET);
        System.out.println("1-Start 2-Logout 3-Setting");
        choose = scanner.nextLine();
        if (choose.equals("1")) {
            for (int i = 0; i < 5; i++) {
                if (levels[i].equals("1"))
                    System.out.print((i + 1) + " : open, ");
                else
                    System.out.print((i + 1) + " : close, ");
            }
            System.out.println();
            System.out.println("enter your level you can Enter (back) to return to menu!!!");
            level = scanner.nextLine();
            while (!isNumberic(level) && !level.equals("back")) {
                System.out.println(ConsoleColors.RED + "your input must be number :|" + ConsoleColors.RESET);
                level = scanner.nextLine();
            }
            if (isNumberic(level)) {
                if (Integer.parseInt(level) > 5 || Integer.parseInt(level) <= 0) {
                    System.out.println(ConsoleColors.RED + "your number must be between 1 and 5 !!!" + ConsoleColors.RESET);
                    startMenu(name);
                } else if (Integer.parseInt(levels[Integer.parseInt(level) - 1]) == 1) {
                    run(Integer.parseInt(level));
                } else {
                    System.out.println(ConsoleColors.RED + "Your inpute level is locked!!!" + ConsoleColors.RESET);
                    startMenu(name);
                }
            } else if (level.equals("back")) {
                startMenu(name);
            }
        } else if (choose.equals("2")) {
            menu();
        } else if (choose.equals("3")) {
            setting();
        } else {
            System.out.println(ConsoleColors.RED + "your input invalid!!" + ConsoleColors.RESET);
            startMenu(name);
        }
    }

    public void setting() {

    }

    private void truckLoad(String[] input) {
        int amount;
        System.out.println(ConsoleColors.PURPLE + "Enter your amount : " + ConsoleColors.RESET);
        amount = scanner.nextInt();
        String name = input[2];
        if (amount < 0) {
            System.out.println(ConsoleColors.RED + "must be  positive !" + ConsoleColors.RESET);
            truckLoad(input);
            return;
        }
        manager.truckLoad(name, amount);
    }

    private void truckGo() {
        manager.truckGo();
    }

    private String[] fileCopy(File file) {
        int i = 0;
        String line = "";
        String[] fileStringArray = new String[1000];
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                fileStringArray[i] = line;
                i++;
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        return fileStringArray;
    }

    private void fileChange(String[] lines, int level, String username) {
        int i = 0, linesLength = 0;
        while (lines[i] != null) {
            String line = lines[i];
            Object object = parser.parse(line);
            JsonObject jsonObject = (JsonObject) object;
            String user = jsonObject.get("username").toString();
            String pass = jsonObject.get("username").toString();
            user = user.substring(1, user.length() - 1);
            pass = pass.substring(1, pass.length() - 1);
            if (user.equals(username)) {
                String newLevel = "";
                if (level == 2) {
                    newLevel = "[1,1,0,0,0]";
                } else if (level == 3) {
                    newLevel = "[1,1,1,0,0]";
                } else if (level == 4) {
                    newLevel = "[1,1,1,1,0]";
                } else if (level == 5) {
                    newLevel = "[1,1,1,1,1]";
                }
                lines[i] = "{\"levels\":" + newLevel + ",\"username\":\"" + user + "\",\"pass\":\"" + pass + "\"}";
                break;
            }
            i++;
        }
        linesLength = i;
        i = 0;
        while (lines[i] != null) {
            try {
                if (i == 0) {
                    FileWriter fileWriter2 = new FileWriter(file);
                    fileWriter2.write("");
                    fileWriter2.close();
                }
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(lines[i]);
                if (i != (linesLength - 1))
                    bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("eRRor");
            }
            i++;
        }
    }

}
