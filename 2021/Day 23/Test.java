public class Test {
    public static void main(String[] args) {
        String a = "..........";
        String b = "..";
        String c = "....A....C..";
        String regex = "\\.+";
        

        System.out.println(a.matches(regex));
        System.out.println(b.matches(regex));
        System.out.println(c.matches(regex));
        System.out.println("ABCD".matches("[A-Z]+"));
        
        Hallway hallway = new Hallway("A..........");
        System.out.println(hallway.getValidMovesFromCharToRoom());
        System.out.println(hallway.getValidMovesFromPos(4,'A'));
    }
}