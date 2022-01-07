import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        List<String> pairsList = Util.readAllPairs("input.txt");
        
        int maxMagnitude = Integer.MIN_VALUE;
        for(int i=0; i<pairsList.size(); i++) { 
            for(int j=0; j<pairsList.size(); j++) {
                if(i==j) continue;

                PairNode tree1 = Util.createPairTree(pairsList.get(i));
                PairNode tree2 = Util.createPairTree(pairsList.get(j));
                
                PairNode combinedTree = Util.combineTrees(tree1, tree2);
                Util.explodeAndSplitTree(combinedTree);

                int combinedMagnitude = combinedTree.calculateMagnitude();

                maxMagnitude = Math.max(maxMagnitude, combinedMagnitude);
            }
        }

        System.out.println("Answer: " + maxMagnitude);
    }
}