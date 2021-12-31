public class Player {
    int position;
    int score;

    Player(int position) {
        this.position = position;
        score = 0;
    }

    void incrementPositionAndScore(int val) {
        position += val;

        if(position > 10) {
            position = position % 10 == 0 ? 10 : position%10;
        }

        score += position;
    }

    @Override
    public String toString() {
        return "(Score: " + score + ", Position: " + position + ")";
    }
}