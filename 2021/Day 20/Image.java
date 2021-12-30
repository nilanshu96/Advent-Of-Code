import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Image {
    private List<StringBuilder> image = Collections.emptyList();
    static final Image EMPTY_IMAGE = new Image();
    static final char LIGHT = '#';
    static final char DARK = '.';

    private Image() {}

    Image(List<String> image) {
        this.image = new ArrayList<>();

        for(int i=0; i<image.size(); i++) {
            this.image.add(new StringBuilder(image.get(i)));
        }
    }

    Image(Image img) {
        this.image = new ArrayList<>();

        for(int i=0; i<img.image.size(); i++) {
            this.image.add(new StringBuilder(img.image.get(i)));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(StringBuilder s: image) {
            sb.append(s.toString() + "\n");
        }

        return sb.toString();
    }

    public void expandImageOnEachSideByPixels(int pixels, char outsidePixel) {
        if(image == EMPTY_IMAGE || image.isEmpty()) return;

        for(StringBuilder sb: image) {
            sb.insert(0,String.join("",Collections.nCopies(pixels,String.valueOf(outsidePixel))));
            sb.append(String.join("",Collections.nCopies(pixels,String.valueOf(outsidePixel))));
        }

        int length = image.get(0).length();
        for(int i=0; i<pixels; i++) {
            image.add(0,new StringBuilder(String.join("",Collections.nCopies(length,String.valueOf(outsidePixel)))));
            image.add(new StringBuilder(String.join("",Collections.nCopies(length,String.valueOf(outsidePixel)))));
        }
    }

    public void trimByCharacter(char c) {
        int i=0, count=0;
        int rowLength = getNumOfRows();
        int colLength = getNumOfCols();
        String str;

        String compareString = String.join("",Collections.nCopies(colLength, String.valueOf(c)));

        //trim top rows
        while((image.get(i).toString().equals(compareString))){
            i++;
            count++;
        }

        while(count-- > 0) {
            image.remove(0);
        }

        i=image.size()-1;
        count = 0;

        //trim bottom rows
        while((image.get(i).toString().equals(compareString))){
            i--;
            count++;
        }

        while(count-- > 0) {
            image.remove(image.size()-1);
        }

        compareString = String.join("",Collections.nCopies(image.size(), String.valueOf(c)));

        //trim left columns
        i=0; count=0;
        while(getColSubstring(i,0,image.size()).equals(compareString)) {
            i++;
            count++;
        }

        for(StringBuilder sb: image) {
            sb.delete(0,count);
        }

        //trim right columns
        i=image.get(0).length()-1;
        count = 0;

        while(getColSubstring(i,0,image.size()).equals(compareString)) {
            i--;
            count++;
        }

        for(StringBuilder sb: image) {
            sb.delete(sb.length()-count,sb.length());
        }
    }

    public char get(int i, int j) {
        return image.get(i).charAt(j);
    }

    //c1 inclusive, c2 exclusive
    public String getRowSubstring(int r, int c1, int c2) {
        return image.get(r).substring(c1,c2);
    }

    public String getColSubstring(int c, int r1, int r2) {
        StringBuilder sb = new StringBuilder();

        for(int i=r1; i<r2; i++) {
            sb.append(get(i,c));
        }

        return sb.toString();
    }

    public void set(int i, int j, char c) {
        image.get(i).setCharAt(j,c);
    }

    public int getNumOfRows() {
        return (image == null) ? -1: image.size();
    }

    public int getNumOfCols() {
        return (image == null || image == EMPTY_IMAGE) ? 0: image.get(0).length();
    }
}