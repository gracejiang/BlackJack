import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static Scanner scan = new Scanner(System.in);

    // returns amount user bets
    public static int bet(int coins) {
        System.out.println("You have " + Color.BLUE_BOLD + coins + Color.RESET + " coins. ");
        System.out.print(Color.CYAN_BOLD + "Enter Bet Amount: " + Color.RESET);

        while (true) {
            String input = scan.nextLine();
            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0 || amount > coins) {
                    System.out.print(Color.RED + "Please enter a valid integer between 1 - "
                            + coins + ": " + Color.RESET);
                } else {
                    System.out.println("You bet " + Color.BLUE_BOLD + amount + Color.RESET + " coins this round!\n");
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.print(Color.RED + "Please enter a valid integer between 1 - "
                        + coins + ": " + Color.RESET);
            }
        }
    }

    // returns what the user chooses to do that turn
    public static String choice(List<String> options) {
        while (true) {
            String input = scan.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice <= 0 || choice > options.size()) {
                    System.out.print(Color.RED + "Please enter a valid integer between 1 - "
                            + options.size() + ": " + Color.RESET);
                } else {
                    return options.get(choice - 1);
                }
            } catch (NumberFormatException e) {
                System.out.print(Color.RED + "Please enter a valid integer between 1 - "
                        + options.size() + ": " + Color.RESET);
            }
        }
    }

    // returns true if user wants to continue playing and false otherwise
    public static boolean keepPlaying() {
        while (true) {
            String input = scan.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                return true;
            } else if (input.equals("N") || input.equals("n")) {
                System.out.println(Color.BLUE_BOLD + "\nThanks for playing!" + Color.RESET);
                return false;
            } else {
                System.out.print(Color.RED + "Please enter " + Color.RED_BOLD + "Y" + Color.RED +
                        " or " + Color.RED_BOLD + "N" + Color.RED + ": " + Color.RESET);
            }
        }
    }
}
