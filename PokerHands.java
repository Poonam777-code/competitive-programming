import java.util.*;
public class PokerHands {

    static class Hand implements Comparable<Hand> {
        int[] rank = new int[5];
        char[] suit = new char[5];
        int[] count = new int[15]; 
        int handType;
        int[] tiebreaker = new int[5];
        public Hand(String[] cards) {
            for (int i = 0; i < 5; i++) {
                char r = cards[i].charAt(0);
                char s = cards[i].charAt(1);
                suit[i] = s;
                rank[i] = toRank(r);
                count[rank[i]]++;
            }
            evaluate();
        }
        private int toRank(char r) {
            if (r >= '2' && r <= '9') return r - '0';
            switch (r) {
                case 'T': return 10;
                case 'J': return 11;
                case 'Q': return 12;
                case 'K': return 13;
                case 'A': return 14;
            }
            return 0;
        }
        private void evaluate() {
            boolean flush = true;
            for (int i = 1; i < 5; i++) {
                if (suit[i] != suit[0]) {
                    flush = false;
                    break;
                }
            }
            List<Integer> ranksList = new ArrayList<>();
            for (int i = 14; i >= 2; i--) {
                for (int j = 0; j < count[i]; j++) {
                    ranksList.add(i);
                }
            }
            boolean straight = false;
            for (int i = 14; i >= 5; i--) {
                boolean isStraight = true;
                for (int j = 0; j < 5; j++) {
                    if (count[i - j] != 1) {
                        isStraight = false;
                        break;
                    }
                }
                if (isStraight) {
                    straight = true;
                    ranksList.clear();
                    ranksList.add(i);
                    break;
                }
            }

            int four = -1, three = -1;
            List<Integer> pairs = new ArrayList<>();
            for (int i = 14; i >= 2; i--) {
                if (count[i] == 4) four = i;
                if (count[i] == 3) three = i;
                if (count[i] == 2) pairs.add(i);
            }
            if (straight && flush) {
                handType = 8;
            } else if (four != -1) {
                handType = 7;
                ranksList.clear();
                ranksList.add(four);
                for (int i = 14; i >= 2; i--) {
                    if (i != four && count[i] > 0) ranksList.add(i);
                }
            } else if (three != -1 && pairs.size() > 0) {
                handType = 6;
                ranksList.clear();
                ranksList.add(three);
                ranksList.add(pairs.get(0));
            } else if (flush) {
                handType = 5;
            } else if (straight) {
                handType = 4;
            } else if (three != -1) {
                handType = 3;
                ranksList.clear();
                ranksList.add(three);
                for (int i = 14; i >= 2; i--) {
                    if (i != three && count[i] > 0) ranksList.add(i);
                }
            } else if (pairs.size() == 2) {
                handType = 2;
                Collections.sort(pairs, Collections.reverseOrder());
                ranksList.clear();
                ranksList.addAll(pairs);
                for (int i = 14; i >= 2; i--) {
                    if (!pairs.contains(i) && count[i] > 0) ranksList.add(i);
                }
            } else if (pairs.size() == 1) {
                handType = 1;
                ranksList.clear();
                ranksList.add(pairs.get(0));
                for (int i = 14; i >= 2; i--) {
                    if (i != pairs.get(0) && count[i] > 0) ranksList.add(i);
                }
            } else {
                handType = 0;
            }

            for (int i = 0; i < ranksList.size(); i++) {
                tiebreaker[i] = ranksList.get(i);
            }
        }

        @Override
        public int compareTo(Hand o) {
            if (this.handType != o.handType) {
                return Integer.compare(this.handType, o.handType);
            }
            for (int i = 0; i < 5; i++) {
                if (this.tiebreaker[i] != o.tiebreaker[i]) {
                    return Integer.compare(this.tiebreaker[i], o.tiebreaker[i]);
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] blackCards = new String[5];
            String[] whiteCards = new String[5];
            for (int i = 0; i < 5; i++) {
                if (!sc.hasNext()) return;
                blackCards[i] = sc.next();
            }
            for (int i = 0; i < 5; i++) {
                if (!sc.hasNext()) return;
                whiteCards[i] = sc.next();
            }

            Hand black = new Hand(blackCards);
            Hand white = new Hand(whiteCards);
            int cmp = black.compareTo(white);

            if (cmp > 0) {
                System.out.println("Black wins.");
            } else if (cmp < 0) {
                System.out.println("White wins.");
            } else {
                System.out.println("Tie.");
            }
        }
    }
}
