package BST;

import java.util.*;

public class LevelOrderTree {


    static class Node {
        int key;
        Node left;
        Node right;
    }

    ;

    static Node root = null;

    static Queue<Node> q = new LinkedList<>();

    // Function to create a node
// with 'value' as the data
// stored in it.
// Both the children of this
// new Node are initially null.
    static Node newNode(int value) {
        Node n = new Node();
        n.key = value;
        n.left = null;
        n.right = null;
        return n;
    }

    static void insertValue(int value) {
        Node node = newNode(value);
        if (root == null)
            root = node;

            // The left child of the
            // current Node is used
            // if it is available.
        else if (q.peek().left == null)
            q.peek().left = node;

            // The right child of the current
            // Node is used if it is available.
            // Since the left child of this
            // node has already been used, the
            // Node is popped from the queue
            // after using its right child.
        else {
            q.peek().right = node;
            q.remove();
        }

        // Whenever a new Node is added
        // to the tree, its address is
        // pushed into the queue. So that
        // its children Nodes can be used
        // later.
        q.add(node);

    }

    // This function mainly calls
// insertValue() for all array
// elements. All calls use same
// queue.
    static void createTree(int arr[],int n) {
        for (int i = 0; i < n; i++)
            insertValue(arr[i]);
    }

    // This is used to verify
// the logic.
    static void levelOrder(Node root) {
        if (root == null)
            return;
        Queue<Node> n =
                new LinkedList<>();
        n.add(root);
        while (!n.isEmpty()) {
            System.out.print(n.peek().key +
                    " ");
            if (n.peek().left != null)
                n.add(n.peek().left);
            if (n.peek().right != null)
                n.add(n.peek().right);
            n.remove();
        }
    }

    // Driver code
    public static void main(String[] args) {
        int arr[] = {10, 20, 30,
                40, 50, 60};
        int n = arr.length;
        createTree(arr, n);
        levelOrder(root);
    }
}

// This code is contributed by Rajput-Ji

