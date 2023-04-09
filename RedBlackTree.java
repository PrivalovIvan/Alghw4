public class RedBlackTree<T extends Comparable<T>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int size;

    private class Node {
        T key;
        Node left;
        Node right;
        boolean color;

        public Node(T key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }

    public void add(T key) {
        root = add(root, key);
        root.color = BLACK;
    }

    private Node add(Node node, T key) {
        if (node == null) {
            size++;
            return new Node(key, RED);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key);
        }

        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}
