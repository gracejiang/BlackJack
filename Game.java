import java.util.ArrayList;
import java.util.List;

public class Game {

    private static List<Card> deck = new ArrayList<>();
    private static List<Card> dealerHand = new ArrayList<>();
    private static List<Card> playerHand= new ArrayList<>();

    // stored options as a list so that future implementations can add
    // other black jack functions, like DOUBLE, SPLIT, and SURRENDER
    private static List<String> options = new ArrayList<>();

    private static int coins = 1000; // number of coins player has left
    private static int shuffleCounter = 0; // shuffles deck once all cards have been rotated through

    // play game function
    private static void playGame() {
        while (coins > 0) {
            playRound();
            if (coins > 0) {
                System.out.println("\n–––––––––––––––––––––––––––––––––––––––––––––––\n");
                System.out.print(Color.CYAN_BOLD + "Play one more round? (Y/N): " + Color.RESET);
                if (!InputHandler.keepPlaying()) {
                    return;
                }
            }
        }
    }

    // plays one round of blackjack
    private static void playRound() {
        System.out.println("\n–––––––––––––––––––––––––––––––––––––––––––––––\n");

        int betAmount = InputHandler.bet(coins);

        dealerHand.clear();
        playerHand.clear();

        dealerHand.add(drawTopCard());
        dealerHand.add(drawTopCard());

        playerHand.add(drawTopCard());
        playerHand.add(drawTopCard());

        System.out.println(Color.PURPLE_BOLD +"Dealer's Hand: " + Color.RESET + "[" + dealerHand.get(0) + "] [?]");
        PrintStatements.printPlayerHand(playerHand);

        if (isBlackJack(playerHand)) {
            System.out.println(Color.GREEN_BOLD + "\nYOU HIT BLACKJACK!!!" + Color.RESET + "\n");
            System.out.println("You win " + Color.BLUE_BOLD + 3 * betAmount / 2 + Color.RESET + " coins.");
            coins += 3 * betAmount / 2;
            return;
        }

        int result = userTurn();

        System.out.println();
        switch (result) {
            case -1:
                coins -= betAmount;
                System.out.println(Color.RED_BOLD + "You lost!" + Color.RESET + " You now have " + Color.BLUE_BOLD + coins + Color.RESET + " coins.");
                break;

            case 0:
                System.out.println("You tied. You still have " + Color.BLUE_BOLD + coins + Color.RESET + " coins.");
                break;

            case 1:
                coins += betAmount;
                System.out.println(Color.GREEN_BOLD + "You won!" + Color.RESET + " You now have " + Color.BLUE_BOLD + coins + Color.RESET + " coins.");
                break;

            default:
                break;
        }

    }

    // returns true if user is dealt a blackjack and false otherwise
    private static boolean isBlackJack(List<Card> cards) {
        if (cards.size() != 2) {
            return false;
        }

        return (sum(cards) == 21);
    }

    // returns 1 if user wins, -1 if user loses, and 0 if user ties
    private static int userTurn() {
        calcOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }

        System.out.print(Color.CYAN_BOLD + "Enter Choice: " + Color.RESET);
        String choice = InputHandler.choice(options);

        switch(choice) {
            case "HIT":
                System.out.println();
                playerHand.add(drawTopCard());
                PrintStatements.printPlayerHand(playerHand);

                if (sum(playerHand) > 21) {
                    return -1;
                }

                return userTurn();

            case "STAND":
                while (sum(dealerHand) < 17) {
                    dealerHand.add(drawTopCard());
                }

                int dealerSum = sum(dealerHand);
                int playerSum = sum(playerHand);

                System.out.println();
                PrintStatements.printDealerHand(dealerHand);

                if (dealerSum > 21 || playerSum > dealerSum) {
                    return 1;
                } else if (dealerSum > playerSum) {
                    return -1;
                } else {
                    return 0;
                }

            default:
                break;
        }

        return 0;
    }

    // calculates user's options
    // should be updated in future implementations to include DOUBLE, SPLIT, and SURRENDER
    private static void calcOptions() {
        options.clear();
        options.add("HIT");
        options.add("STAND");
    }

    // calculates the sum of a hand (accounting for A being both 1 and 11)
    public static int sum(List<Card> cards) {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            sum += cards.get(i).getValue();
        }

        if (sum > 21 && hasAce(cards)) {
            sum -= 10;
        }

        return sum;
    }

    // returns true if hand has an A and false otherwise
    private static boolean hasAce(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() == 11) {
                return true;
            }
        }
        return false;
    }

    // sets up deck of cards
    private static void setUp() {

        // add cards to deck (using 2 decks instead of 1 for this game!)
        for (int i = 0; i < 8; i++) {
            deck.add(new Card("A"));

            for (int j = 2; j <= 10; j++) {
                deck.add(new Card(j + ""));
            }

            deck.add(new Card("J"));
            deck.add(new Card("Q"));
            deck.add(new Card("K"));
        }

        // shuffle deck
        shuffleDeck();
    }

    // returns top card and moves it to the bottom of the deck
    // shuffles deck when all cards have been rotated through
    private static Card drawTopCard() {
        Card c = deck.get(0);
        deck.remove(0);
        deck.add(c);
        shuffleCounter++;
        if (shuffleCounter >= deck.size()) {
            shuffleDeck();
        }
        return c;
    }

    // shuffles deck
    private static void shuffleDeck() {
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * size);
            Card temp = deck.get(index);
            deck.set(index, deck.get(i));
            deck.set(i, temp);
        }
    }

    // main function to run the game!
    public static void main(String[] args) {
        setUp();
        PrintStatements.printIntro();
        playGame();
    }

}
