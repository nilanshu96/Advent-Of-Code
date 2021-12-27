class PairNode {
    int first,second;
    PairNode left,right;
    PairNode parent;

    PairNode() {
        first = second = -1;
        left = right = null;
        parent = null;
    }

    int calculateMagnitude() {

        if(left != null && right != null) {
            return 3*left.calculateMagnitude() + 2*right.calculateMagnitude();
        } else if(left != null) {
            return 3*left.calculateMagnitude() + 2*second;
        } else if(right != null) {
            return 3*first + 2*right.calculateMagnitude();
        } else {
            return 3*first + 2*second;
        }

    }

    boolean isLeaf() {
        return (left == null) && (right == null);
    }

    @Override
    public String toString() {
        // Printing is done inorder
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(left != null) {
            sb.append(left.toString());
        } else {
            sb.append(first);
        }
        sb.append(",");
        if(right != null) {
            sb.append(right.toString());
        } else {
            sb.append(second);
        }
        sb.append("]");
        return sb.toString();
    }
}