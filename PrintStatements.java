import java.util.List;

public class PrintStatements {

    // prints introduction of blackjack
    public static void printIntro() {
        System.out.println(Color.BLUE_BOLD + "Welcome to BlackJack!\n" + Color.RESET);
        System.out.println("- The objective of this game is to get a higher score than the dealer.");
        System.out.println("- Your score is the sum of the cards in your hand.");
        System.out.println("- " + Color.PURPLE_BOLD + "[A]" + Color.RESET + " card can be 1 or 11 points, and "
                + Color.PURPLE_BOLD + "[J] [Q] [K]" + Color.RESET + " cards are 10 points.");
        System.out.println("- Cards " + Color.PURPLE_BOLD + "[2]" + Color.RESET + " through "
                + Color.PURPLE_BOLD + "[10]" + Color.RESET + " have their face value number of points.");
        System.out.println("- Each round, you have the option to " + Color.CYAN_BOLD + "HIT"
                + Color.RESET + " (draw another card) or " + Color.CYAN_BOLD + "STAND"
                + Color.RESET + " (end turn).");
        System.out.println("- If your score ends up over 21, you " + Color.RED_BOLD + "BUST"
                + Color.RESET + " and automatically lose the round.");
        System.out.println("- Otherwise, the dealer draws until their score is 17 or above.");
        System.out.println("- For the full set of rules, please look online!");

        System.out.println(Color.BLUE_BOLD + "\nLet's get started!" + Color.RESET);
    }

    // prints the dealer's hand
    public static void printDealerHand(List<Card> dealerHand) {
        int size = dealerHand.size();
        System.out.print(Color.PURPLE_BOLD + "Dealer's Hand: " + Color.RESET);
        for (int i = 0; i < size; i++) {
            System.out.print("[" + dealerHand.get(i) + "] ");
        }
        System.out.println(Color.PURPLE_BOLD + "\nDealer's Score: " + Color.RESET + Game.sum(dealerHand));
    }

    // prints the player's hand
    public static void printPlayerHand(List<Card> playerHand) {
        int size = playerHand.size();
        System.out.print(Color.PURPLE_BOLD + "Your Hand: " + Color.RESET);
        for (int i = 0; i < size; i++) {
            System.out.print("[" + playerHand.get(i) + "] ");
        }
        System.out.println(Color.PURPLE_BOLD + "\nYour Score: " + Color.RESET + Game.sum(playerHand));
    }

}
