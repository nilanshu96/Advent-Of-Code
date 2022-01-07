import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        List<Point> points = Util.readAllPoints(inputFile);
        List<FoldLine> foldLines = Util.readAllFoldLines(inputFile);

        Point largestPoint = Util.getLargestPoint(points);

        int[][] matrix = Util.getMatrixFromPoints(points, largestPoint);

        matrix = foldOrigami(matrix, foldLines.get(0));

        System.out.println("Answer: " + Util.countDots(matrix));
    }

    private static int[][] foldOrigami(int[][] matrix, FoldLine line) {

        int foldPos = line.getValue();
        int[][] mat1, mat2;

        if(line.isVertical()){
            mat2 = Util.copyIntMatrix(matrix, foldPos + 1, matrix[0].length - 1, 0, matrix.length -1);
            mat1 = Util.copyIntMatrix(matrix, 0, foldPos - 1, 0, matrix.length - 1);
        } else {
            mat2 = Util.copyIntMatrix(matrix, 0, matrix[0].length - 1, foldPos + 1, matrix.length - 1);
            mat1 = Util.copyIntMatrix(matrix, 0, matrix[0].length - 1, 0, foldPos - 1);
        }

        Util.flipIntMatrix(mat2, line.isVertical());
        return Util.combineMatrix(mat1, mat2, line.isVertical());
    }
}