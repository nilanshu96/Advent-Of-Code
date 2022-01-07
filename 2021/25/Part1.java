public class Part1 {

    static final char eastCucumber = '>';
    static final char southCucumber = 'v';
    static final char emptySpace = '.';

    public static void main(String[] args) {
        char[][] cucumberHerd = Util.readCucumberHerd("input.txt");

        int moveCounts, steps = 0;

        do {
            moveCounts = 0;
            moveCounts += moveEast(cucumberHerd);
            moveCounts += moveSouth(cucumberHerd);

            steps++;
        } while(moveCounts > 0);

        // Util.printCucumberHerd(cucumberHerd);
        System.out.println("Answer: " + steps);
    }

    static int moveEast(char[][] cucumberHerd) {
        if(cucumberHerd == null || cucumberHerd.length == 0 || cucumberHerd[0].length == 0) return 0;
        
        int count = 0;
        final int columns = cucumberHerd[0].length;
        for(int i=0; i<cucumberHerd.length; i++) {
            //Because the characters are getting modified from the beginning index to the last index,
            //when the last index character checks for the beginning index character, there's a chance it might have gotten modified
            //Hence I'm storing the first charater here
            char firstChar = cucumberHerd[i][0];
            for(int j=0; j<columns; j++) {
                int nextIdx = (j+1)%columns;
                
                if(cucumberHerd[i][j] == eastCucumber) {
                    if(nextIdx == 0 && firstChar == emptySpace) {
                        cucumberHerd[i][nextIdx] = eastCucumber;
                        cucumberHerd[i][j] = emptySpace;
                        count++;
                    } else if(nextIdx > 0 && cucumberHerd[i][nextIdx] == emptySpace) {
                        cucumberHerd[i][nextIdx] = eastCucumber;
                        cucumberHerd[i][j] = emptySpace;
                        j++; //because two columns are getting processed at the same time in this step
                        count++;
                    }
                }
            }
        }

        return count;
    }

    static int moveSouth(char[][] cucumberHerd) {
        if(cucumberHerd == null || cucumberHerd.length == 0 || cucumberHerd[0].length == 0) return 0;
        
        int count = 0;
        final int columns = cucumberHerd[0].length;
        for(int i=0; i<columns; i++) {
            char firstChar = cucumberHerd[0][i];
            for(int j=0; j<cucumberHerd.length; j++) {
                int nextIdx = (j+1)%cucumberHerd.length;

                if(cucumberHerd[j][i] == southCucumber) {
                    if(nextIdx == 0 && firstChar == emptySpace) {
                        cucumberHerd[nextIdx][i] = southCucumber;
                        cucumberHerd[j][i] = emptySpace;
                        count++;
                    } else if(nextIdx > 0 && cucumberHerd[nextIdx][i] == emptySpace) {
                        cucumberHerd[nextIdx][i] = southCucumber;
                        cucumberHerd[j][i] = emptySpace;
                        j++; //because two rows are getting processed at the same time in this step
                        count++;
                    }
                }  
            }
        }

        return count;
    }
}