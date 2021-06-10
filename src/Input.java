import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

    static Manager manager = new Manager();
    static Scanner scanner = new Scanner(System.in);
    String nowUserName = "";
    FileWriter fileWriter;
    FileReader fileReader;
    File file=new File("property");
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

    public void run() {
        String order;
        boolean finish = false;
        System.out.println(ConsoleColors.YELLOW + "GAME START!" + ConsoleColors.RESET);

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
            } else if (Pattern.compile("[tT][rR][uU][Kk] [lL][oO][aA][Dd] \\w+").matcher(order).find()) {

            } else if (Pattern.compile("[tT][rR][uU][cC][kK] [uU][nN][lL][oO][aA][dD] \\w+").matcher(order).find()) {
                truckUnload(order.split("\\s"));
            } else if (Pattern.compile("[tT][rR][uU][cC][kK] [Gg][Oo]").matcher(order).find()) {
                truckGo();
            }
            else if (Pattern.compile("[iI][nN][qQ][uU][iI][rR][Yy]").matcher(order).find()){
                manager.show();
            } else {
                System.out.println(ConsoleColors.RED + "wrong command!" + ConsoleColors.RESET);
            }

        }
    }

    private void truckUnload(String[] input){
        String name=input[2];
        int am=0;
        System.out.println("enter your amount");
        manager.truckUnload(name,am);
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
        String pass ;
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
                if (file.length()!=0)
                    bufferedWriter.newLine();

                User user = new User(name, pass);
                gson.toJson(user, bufferedWriter);
                bufferedWriter.close();
                System.out.println(ConsoleColors.CYAN + "sign up successfully done :)" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.PURPLE + "Enter the game with choose the login :)" + ConsoleColors.RESET);
                menu();
            } catch (IOException exception) {
                System.err.println("Error in opening account!!!");
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
            String line = "";
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
                        startMenu();
                    } else {
                        System.err.println("your pass incorrect ReEnter your pass you can write (back) to go to menu:||");
                        pass = scanner.nextLine();
                        while (!passCertificate.substring(1, passCertificate.length() - 1).equals(pass) && !pass.equals("back")) {
                            System.err.println("your pass word is wrong you can write (back) to go to menu :||");
                            pass = scanner.nextLine();
                            if (pass.equals("back")) {
                                menu();
                                return;
                            }
                        }
                        run();
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

    public void startMenu() {
        String level ;
        String choose ;
        System.out.println(ConsoleColors.GREEN + "Enter your order :)" + ConsoleColors.RESET);
        System.out.println("1-Start 2-Logout 3-Setting");
        choose = scanner.nextLine();
        if (choose.equals("1")) {
            System.out.println("enter your level you can Enter (back) to return to menu!!!");
            level = scanner.nextLine();
            while (!isNumberic(level) && !level.equals("back")) {
                System.out.println(ConsoleColors.RED + "your input must be number :|" + ConsoleColors.RESET);
                level = scanner.nextLine();
            }
            if (isNumberic(level))
            {
                run();
            }
            else if (level.equals("back")) {
                startMenu();
            }
        } else if (choose.equals("2")) {
            menu();
        } else if (choose.equals("3")) {
            setting();
        } else {
            System.out.println(ConsoleColors.RED + "your input invalid!!" + ConsoleColors.RESET);
            startMenu();
        }
    }

    public void setting() {

    }

    private void truckLoad(String[] input) {
        int amount = 0;
        System.out.println(ConsoleColors.PURPLE + "Enter your amount !!!");
        amount = scanner.nextInt();
        String name = input[2];
        manager.truckLoad(name, amount);
    }

    private void truckGo(){
        manager.truckGo();
    }

}



