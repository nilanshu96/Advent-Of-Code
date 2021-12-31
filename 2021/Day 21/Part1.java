public class Part1 {
    public static void main(String[] args) {
        Pair<Player,Player> players = Util.readPlayersFromFile("input.txt");
        System.out.println(players);
        
        for(int i=0; players.first.score < 1000 && players.second.score < 1000; i++) {
            int diceRollSum = DeterministicDice.tripleRollSum();
            if(i%2 == 0) {
                players.first.incrementPositionAndScore(diceRollSum);
            } else {
                players.second.incrementPositionAndScore(diceRollSum);
            }
        }

        System.out.println(players);
        int loserScore = Math.min(players.first.score, players.second.score);
        System.out.println("Answer: " + loserScore * DeterministicDice.getRollCount());
        
    }

    private static class DeterministicDice {
        private static int val = 0;
        private static int rollCount = 0;

        private DeterministicDice() {}

        static int roll() {
            rollCount++;
            val++;
            if(val > 100) {
                val = val%100;
            }
            return val;
        }

        static int tripleRollSum() {
            int sum = 0;
            for(int i=0; i<3; i++) {
                sum += roll();
            }
            return sum;
        }

        static int getRollCount() {
            return rollCount;
        }
    }
}