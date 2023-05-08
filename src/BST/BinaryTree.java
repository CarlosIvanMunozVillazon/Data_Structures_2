package BST;

public class BinaryTree {

    private static class Node {

        private Node left;
        private int data;
        private Node right;

        public Node() {

        }

        public Node(int data) {
            left = null;
            this.data = data;
            right = null;
        }
    }

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    private void inOrder(Node root) {

        if (root == null) {
            System.out.print("");
        } else {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }


        /*if (root.left != null && root.right != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        } else {
            System.out.println(root.data);
        }*/

//        System.out.println(root.data);

        /*if (root.right != null) {
            inOrder(root.left);
        }*/
    }

    public void printInOrder(Node root) {
        System.out.println("In order: ");
        inOrder(root);
        System.out.println();
    }

    private void preOrder(Node root) {
        System.out.println(root.data);

        if (!(root.left == null || root.right == null)) {
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void printPreOrder(Node root) {
        System.out.println("Pre order: ");
        preOrder(root);
        System.out.println();
    }

    private void postOrder(Node root) {

        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        } else {
            System.out.print("");
        }

        /*if (!(root.left == null || root.right == null)) {
            postOrder(root.left);
            postOrder(root.right);
        }

        System.out.println(root.data);*/
    }

    public void printPostOrder(Node root) {
        System.out.println("Post order: ");
        postOrder(root);
        System.out.println();
    }

    private Node insert(int data, Node root) {

        if (root == null) {
            root = new Node(data);
        } else if (data != root.data) {
            if (data < root.data) {
                root.left = insert(data, root.left);
            } else {
                root.right = insert(data, root.right);
            }
        }

        return root;
    }

    public void insertDriver(int newData) {
        System.out.println("Insertion procedure called");
        this.root = insert(newData, this.root);
        printInOrder(this.root);
        printPostOrder(this.root);
    }

}
