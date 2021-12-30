import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        String filename = "input.txt";
        String algo = Util.readImageEnhancementAlgoString(filename);
        Image image = Util.readImage(filename);

        //Below two decides what the pattern will be for the pixels in infinity
        //0 => 0b000000000
        //511 => 0b111111111
        //for the given input for even iterations, the pixels in infinity are always dark and for odd iterations, they are Light
        for(int i=0; i<50; i++) {
            image = algo.charAt(0) == Image.LIGHT && algo.charAt(511) == Image.DARK? Util.enhanceImage(image,algo, i%2==0?Image.DARK:Image.LIGHT): Util.enhanceImage(image, algo, Image.DARK);
        }

        int count = 0;
        for(int i=0; i<image.getNumOfRows(); i++) {
            for(int j=0; j<image.getNumOfCols(); j++) {
                if(image.get(i,j) == Image.LIGHT) {
                    count++;
                }
            }
        }

        System.out.println("Answer: " + count);
    }
}