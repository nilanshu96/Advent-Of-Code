public class Part1 {
    public static void main(String[] args) {

        int[][] heightMap = Util.readFloorHeightMap("input.txt");
        // Util.print2DIntArray(heightMap);
        int totalRisk = getTotalRiskLevel(heightMap);
        System.out.println("Answer: " + totalRisk);
    }

    private static int getTotalRiskLevel(int[][] heightMap) {
        int riskLevel = 0;

        for(int i=0; i<heightMap.length; i++) {
            for(int j=0; j<heightMap[i].length; j++) {
                if((i==0) && (j==0)) { //Top Left
                    if(heightMap[i][j] < heightMap[i+1][j] && heightMap[i][j] < heightMap[i][j+1]) riskLevel += heightMap[i][j] + 1;
                } else if((i==heightMap.length-1) && (j==0)) { //Bottom left
                    if(heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j+1]) riskLevel += heightMap[i][j] + 1;
                } else if((i==0) && (j==heightMap[i].length-1)) { //Top right
                    if(heightMap[i][j] < heightMap[i+1][j] && heightMap[i][j] < heightMap[i][j-1]) riskLevel += heightMap[i][j] + 1;
                } else if((i==heightMap.length-1) && (j==heightMap[i].length-1)) { //Bottom right
                    if(heightMap[i][j] < heightMap[i-1][j] && heightMap[i][j] < heightMap[i][j-1]) riskLevel += heightMap[i][j] + 1;
                } else if(i==0) { //top
                    int num = heightMap[i][j];
                    if(num < heightMap[i+1][j] && num < heightMap[i][j-1] && num < heightMap[i][j+1]) riskLevel += heightMap[i][j] + 1;
                } else if(i==heightMap.length - 1) { //Bottom
                    int num = heightMap[i][j];
                    if(num < heightMap[i-1][j] && num < heightMap[i][j-1] && num < heightMap[i][j+1]) riskLevel += heightMap[i][j] + 1;
                } else if(j==0) { //Left
                    int num = heightMap[i][j];
                    if(num < heightMap[i][j+1] && num < heightMap[i+1][j] && num < heightMap[i-1][j]) riskLevel += heightMap[i][j] + 1;
                } else if (j==heightMap[i].length-1) { //Right
                    int num = heightMap[i][j];
                    if(num < heightMap[i][j-1] && num < heightMap[i+1][j] && num < heightMap[i-1][j]) riskLevel += heightMap[i][j] + 1;
                } else {
                    int num = heightMap[i][j];
                    if(num < heightMap[i-1][j] && num < heightMap[i][j-1] && num < heightMap[i][j+1] && num < heightMap[i+1][j]) riskLevel += heightMap[i][j] + 1;
                }
            }
        }

        return riskLevel;
    }
}