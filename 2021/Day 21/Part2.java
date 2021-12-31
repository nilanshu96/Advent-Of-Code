public class Part2 {

    static final int MAX_SCORE = 21;
    static final int[] diceSums = new int[10];

    public static void main(String[] args) {
        long[] calc = new long[50];
        Pair<Integer,Integer> players = Util.readPlayersPositionsFromFile("input.txt");

        for(int i=1; i<=3; i++) {
            for(int j=1; j<=3; j++) {
                for(int k=1; k<=3; k++) {
                    diceSums[i+j+k]++; //i,j,k are the 3 rolls. index of diceSums is the sum of rolls.
                    //if there are 6 combos of i,j,k where sum of i,j,k is 5, then a triple dice roll summing to 5 can occur in 6 different universes.
                }
            }
        }

        Pair<Long,Long> playerWins = rollDice(players.first,0,players.second,0);
        System.out.println(playerWins);
        System.out.println("Answer: " + Math.max(playerWins.first, playerWins.second));
    }

    private static Pair<Long,Long> rollDice(int player1Pos, int score1, int player2Pos, int score2) {
        if(score2 >= MAX_SCORE) { //Player 1 is to roll. Hence player 2's score is checked before the roll.
            return new Pair<>(0l,1l);
        }

        long win1 = 0, win2 = 0;
        for(int i=3; i<=9; i++) { //sum of 3 rolls ranges from 3 to 9
            int tempPos = (player1Pos + i)%10 == 0?10:(player1Pos + i)%10;

            //Player 2 is rolling the Dice here. Which is why its position in the method arguments is swapped with player 1
            //so that Player 1's score can be checked before player 2 rolls
            Pair<Long,Long> result = rollDice(player2Pos, score2, tempPos, score1 + tempPos);

            win1 += diceSums[i]*result.second; //result.second because in the above call player2's position was swapped with player 1.
            win2 += diceSums[i]*result.first;
        }

        return new Pair<>(win1,win2);
    }

    
}