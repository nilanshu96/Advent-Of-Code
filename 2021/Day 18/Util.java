import java.util.List;
import java.util.ArrayDeque;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    static List<String> readAllPairs(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch(IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    static PairNode createPairTree(String pairString) {
        if (pairString == null || pairString.isEmpty()) {
            return new PairNode();
        }

        ArrayDeque<PairNode> pairStack = new ArrayDeque<>();
        PairNode currentNode = null;

        for(int i=0; i<pairString.length(); i++) {
            if(pairString.substring(i,i+1).equals("[")) {
                PairNode newNode = new PairNode();
                newNode.parent = currentNode;
                currentNode = newNode;
                pairStack.push(currentNode);
            } else if(pairString.substring(i,i+1).equals("]")) {
                PairNode childNode = pairStack.pop();

                if(pairStack.isEmpty()) {
                    currentNode = childNode;
                    break;
                }

                currentNode = pairStack.peek();

                if(currentNode.first == -1 && currentNode.left == null) {
                    currentNode.left = childNode;
                } else if(currentNode.second == -1 && currentNode.right == null){
                    currentNode.right = childNode;
                }
            } else if(pairString.substring(i,i+2).matches("\\d+,")) {
                int num = Integer.parseInt(pairString.substring(i,i+1));
                currentNode.first = num;
            } else if(pairString.substring(i,i+2).matches(",\\d+")) {
                int num = Integer.parseInt(pairString.substring(i+1,i+2));
                currentNode.second = num;
            }
        }

        return currentNode;
    }

    static void explodeAndSplitTree(PairNode root) {

        while(explodeNode(root,0) || splitNode(root)) { // Java won't execute splitNode if explodeNode is true. That's the advantage of using ||.
            while(explodeNode(root,0)); // explosion takes precedence over splitting as per the reduction instruction. Hence all possible explosions need to be taken care of first.
            splitNode(root); // Needs to be done only once to check if explosion is possible after splitting
        }
    }

    // NOTE: inorder is used because that is the order in which the list gets printed from left to right
    // inorder explosion of leaves. Note: Any order will work that processes left subtree first as leaves won't have both left and right child trees.
    private static boolean explodeNode(PairNode node, int depth) {

        boolean result = false;

        //explode left subtree
        if(node.left != null) {
            result = explodeNode(node.left, depth+1);
        }

        //explode root
        if(node.isLeaf() && depth >= 4) {
            PairNode predecessor = findPredecessor(node);
            if(predecessor != null) {
                if(predecessor.second != -1) {
                    predecessor.second += node.first;
                } else {
                    predecessor.first += node.first;
                }
            }

            PairNode successor = findSuccessor(node);

            if(successor != null) {
                if(successor.first != -1) {
                    successor.first += node.second;
                } else {
                    successor.second += node.second;
                }
            }

            if(node.parent.left == node) {
                node.parent.left = null;
                node.parent.first = 0;
            } else {
                node.parent.right = null;
                node.parent.second = 0;
            }

            result = true;
        }

        //explode right subtree
        if(node.right != null) {
            result = explodeNode(node.right, depth+1) || result;
        }

        return result;
    }

    // finds inorder predecessor
    private static PairNode findPredecessor(PairNode node) {

        if(node == null) return null;

        PairNode currentNode = node;
        PairNode predecessor = currentNode.parent;

        while(predecessor != null && predecessor.left == currentNode) {
            currentNode = predecessor;
            predecessor = predecessor.parent;
        }

        if(predecessor == null) return null;

        if(predecessor.first != -1) return predecessor;

        predecessor = predecessor.left;

        while(predecessor.right != null) {
            predecessor = predecessor.right;
        }

        return predecessor;
    }

    // finds inorder successor
    private static PairNode findSuccessor(PairNode node) {

        if(node == null) return null;

        PairNode currentNode = node;
        PairNode successor = currentNode.parent;

        while(successor != null && successor.right == currentNode) {
            currentNode = successor;
            successor = successor.parent;
        }

        if(successor == null) return null;

        if(successor.second != -1) return successor;
        successor = successor.right;

        while(successor.left != null) {
            successor = successor.left;
        }

        return successor;
    }

    // NOTE: inorder is used because that is the order in which the list gets printed from left to right
    // inorder splitting of trees. The multiple return statements ensure that the function only produces one split at a time.
    private static boolean splitNode(PairNode node) {

        boolean result = false;

        //split left subtree
        if(node.left != null) {
            result = splitNode(node.left);
        }

        if(result) return true; //return if there's a split in left subtree

        //split root
        //returns if the root can be split
        if(node.left == null && node.first >= 10) {
            PairNode newNode = new PairNode();
            newNode.parent = node;
            newNode.first = (int)Math.floor((double)node.first/2);
            newNode.second = (int)Math.ceil((double)node.first/2);

            node.first = -1;
            node.left = newNode;

            return true;
        }

        if(node.right == null && node.second >= 10) {
            PairNode newNode = new PairNode();
            newNode.parent = node;
            newNode.first = (int)Math.floor((double)node.second/2);
            newNode.second = (int)Math.ceil((double)node.second/2);
            node.second = -1;
            node.right = newNode;

            return true;
        }

        //split right subtree
        if(node.right != null) {
            result = splitNode(node.right) || result;
        }

        if(result) return true; //return if there's a split in right subtree

        return false;
    }

    static PairNode combineTrees(PairNode root1, PairNode root2) {

        if(root1 == null) return root2;
        if(root2 == null) return root1;

        PairNode node = new PairNode();
        node.left = root1;
        node.right = root2;

        root1.parent = node;
        root2.parent = node;

        return node;
    }
}