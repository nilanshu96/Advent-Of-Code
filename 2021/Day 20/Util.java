import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Util {

    private static List<String> inputLines;
    private static String bufferedInputFileName;

    private static void readInput(String filename) {
        try {
            inputLines = Files.readAllLines(Paths.get(filename));
            bufferedInputFileName = filename;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    static String readImageEnhancementAlgoString(String filename) {
        if(filename == null) {
            return "";
        }

        if(!filename.equals(bufferedInputFileName)) {
            readInput(filename);
        }

        if(inputLines == null || inputLines.isEmpty()) {
            return "";
        }

        return inputLines.get(0);
    }

    static Image readImage(String filename) {
        if(filename == null) {
            return Image.EMPTY_IMAGE;
        }

        if(!filename.equals(bufferedInputFileName)) {
            readInput(filename);
        }

        if(inputLines == null || inputLines.size() < 2) {
            return Image.EMPTY_IMAGE;
        }

        return new Image(inputLines.subList(2, inputLines.size()));
    }

    static Image enhanceImage(Image image, String enhancementAlgo, char outsidePixel) {
        int extraPixels = 2;
        image.expandImageOnEachSideByPixels(extraPixels, outsidePixel);

        Image newImage = new Image(image);
        int rows = image.getNumOfRows();
        int cols = image.getNumOfCols();

        for(int i=1; i<rows-1; i++) {
            for(int j=1; j<rows-1; j++) {
                StringBuilder sb = new StringBuilder();
                for(int k=0; k<3; k++) {
                    String subStr = image.getRowSubstring(i+k-1,j-1,j+2); //j-1 inclusive, j+2 exclusive
                    subStr = subStr.replaceAll("\\" + String.valueOf(Image.DARK), "0");
                    subStr = subStr.replaceAll("\\" + String.valueOf(Image.LIGHT), "1");
                    sb.append(subStr);
                }
                
                int idx = Integer.parseInt(sb.toString(),2);
                newImage.set(i,j,enhancementAlgo.charAt(idx));
            }
        }
        newImage.trimByCharacter(outsidePixel);
        return newImage;
    }
}