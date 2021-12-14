public class Part1 {
    public static void main(String[] args) {
        int[][] energyLevels = Util.readEnergyLevels("input.txt");
        

        int steps = 100;
        int totalFlashes = 0;

        while(steps-- > 0) {
            incrementStep(energyLevels);
            processEnergyStep(energyLevels);
            totalFlashes += countFlashes(energyLevels);
        }
        System.out.println("Answer: " + totalFlashes);
    }

    private static void incrementStep(int[][] energyLevels) {
        for(int i=0; i<energyLevels.length; i++) {
            for(int j=0; j<energyLevels[i].length; j++) {
                energyLevels[i][j]++;
            }
        }
    }

    private static void processEnergyStep(int[][] energyLevels) {
        int countOf9Plus = 0;

        do {
            countOf9Plus = 0;

            for(int i=0; i<energyLevels.length; i++) {
                for(int j=0; j<energyLevels[i].length; j++) {
                    if(energyLevels[i][j] > 9) {
                        energyLevels[i][j] = 0;
                        incrementNeighbors(energyLevels,i,j);
                    }
                }
            }

            for(int i=0; i<energyLevels.length; i++) 
                for(int j=0; j<energyLevels[i].length; j++)
                    if(energyLevels[i][j] > 9) countOf9Plus++;
            
        } while(countOf9Plus > 0);
    }

    private static void incrementNeighbors(int[][] energyLevels, int x, int y) {
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};

        for(int i=0; i<dx.length; i++) {
            for(int j=0; j<dy.length; j++) {
                if(dx[i]==0 && dy[j]==0) continue;

                int r = x + dx[i];
                int c = y + dy[j];
                if((r<0) || (r >= energyLevels.length)) continue;
                if((c<0) || (c >= energyLevels[x].length)) continue;
                if(energyLevels[r][c] == 0) continue;
                
                energyLevels[r][c]++;
            }
        }
    }

    private static int countFlashes(int[][] energyLevels) {
        int flashes = 0;
        for(int i=0; i<energyLevels.length; i++)
            for(int j=0; j<energyLevels[i].length; j++)
                if(energyLevels[i][j] == 0) flashes++;
        
        return flashes;
    } 
}