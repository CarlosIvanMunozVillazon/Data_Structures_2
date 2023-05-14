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
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;

    }

    private void inOrder(Node root) {

        if (root.left != null) {
            inOrder(root.left);
        }

        System.out.print(root.data + " ");

        if (root.right != null) {
            inOrder(root.right);
        }
    }

    public void printInOrder() {
        System.out.println("In order: ");
        inOrder(this.root);
        System.out.println();
    }

    private void preOrder(Node root) {

        /*if (root != null){
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }*/
        System.out.print(root.data + " ");

        if (root.left != null){
            preOrder(root.left);
        }

        if (root.right != null){
            preOrder(root.right);
        }

    }

    public void printPreOrder() {
        System.out.println("Pre order: ");
        preOrder(this.root);
        System.out.println();
    }

    private void postOrder(Node root) {

        /*if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }*/

        if (root.left != null) {
            postOrder(root.left);
        }

        if (root.right != null) {
            postOrder(root.right);
        }

        System.out.print(root.data + " ");
    }

    public void printPostOrder() {
        System.out.println("Post order: ");
        postOrder(this.root);
        System.out.println();
    }

    private Node insert(int data, Node root) {

        if (root == null) {
            root = new Node(data);
            this.size++;
        } else if (data != root.data) {
            if (data < root.data) {
                root.left = insert(data, root.left);
            } else {
                root.right = insert(data, root.right);
            }
        }

        return root;
    }

    public void insertData(int newData) {
        this.root = insert(newData, this.root);
    }

    //Find the minimum

    private Node findMinimum (Node root) {
        if (root != null){
            while (root.left != null){
                root = root.left;
            }
        }

        return root;
    }

    public int findMinorValue() {
        Node returnNode = findMinimum(this.root);
        return returnNode.data;
    }

    private Node findMaximum (Node root) {
        if (root != null){
            while (root.right != null){
                root = root.right;
            }
        }

        return root;
    }

    public int findMaxValue() {
        Node returnNode = findMaximum(this.root);
        return returnNode.data;
    }

    private Node remove (Node root, int data){

        if (root != null){
            if (data < root.data) { //if data is less than out current root.data we should remove the item on the left
                root.left = remove(root.left, data);
            } else if (data > root.data) { //if data is greater than our current root.data then we should remove item on the right
                root.right = remove(root.right, data);
            } else if (root.right == null && root.left == null){
                //if we found a leaf, then we just should delete the leaf
                root = null;
            } else if (root.right == null){
                //if our data is == to the current root.data and the current root doesn't has right subtree, then we
                //delete de current root by just pointing to the left.
                root = root.left;
            } else if (root.left == null){
                //if our data is == to the current root.data and the current root doesn't has left subtree, then we
                //delete de current root by just pointing to the right.
                root = root.right;
            } else {
                //in case our current root has to sons, then we can get the maximum on the left subtree, or the minimum
                //right subtree the copy it to the current root.data we want to delete, and finally delete the duplicated
                //element we left in the remaining subtree.

                Node temporal = findMaximum(root.left);
                root.data = temporal.data;
                root.left = remove(root.left, root.data);
            }
        } /*else {
            return null;
        }*/

        return root;
    }

    public void removeData (int data){
        this.root = remove(this.root, data);
    }
    public int getSize() {
        return size;
    }
}
