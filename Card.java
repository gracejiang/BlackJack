public class Card implements Comparable {

    private int value = 0;
    private String cardStr;

    Card(String s) {
        cardStr = s;
        try {
            this.value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            if (s.equals("A")) {
                this.value = 11;
            } else if (s.equals("J") || s.equals("Q") || s.equals("K")) {
                this.value = 10;
            }
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return cardStr;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.value, ((Card) o).value);
    }

}
