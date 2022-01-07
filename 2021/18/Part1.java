import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        List<String> pairsList = Util.readAllPairs("input.txt");
        PairNode root = null;

        for(int i=0; i<pairsList.size(); i++) {
            PairNode newTree = Util.createPairTree(pairsList.get(i));
            root = Util.combineTrees(root,newTree);
            Util.explodeAndSplitTree(root);
        }

        System.out.println(root);
        System.out.println("Answer: " + root.calculateMagnitude());
    }
}