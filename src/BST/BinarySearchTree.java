package BST;

import LinkedListWithTail.LinkedListWithTail;

public class BinarySearchTree {

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

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;

    }

    public BinarySearchTree(int[] array) {
        this.root = levelOrderCreate(array);
        this.size = array.length;
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

        //Unnecesary recursion is avoided.
        System.out.print(root.data + " ");

        if (root.left != null) {
            preOrder(root.left);
        }

        if (root.right != null) {
            preOrder(root.right);
        }

    }

    public void printPreOrder() {
        System.out.println("Pre order: ");
        preOrder(this.root);
        System.out.println();
    }

    private void postOrder(Node root) {

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

    private void levelOrder(Node root) {
        LinkedListWithTail<Node> queue = new LinkedListWithTail<Node>(); //we need a queue for keeping track of the elements
        //e.i we "enqueue levels"

        queue.pushBack(root);//first we enqueue the root of our tree

        while (!queue.isEmpty()) {//while the queue isn't empty we continue enqueueing elements of the tree
            //we pick up the first element in the queue.
            Node proofNode = queue.topFront();
            //we delete that element since we already save it in "proofNode"
            queue.popFront();
            //we print our data in the node
            System.out.print(proofNode.data + " ");

            //finally we watch out the other elements in the tree, enqueueing the subtrees.
            if (proofNode.left != null) {
                queue.pushBack(proofNode.left);
            }
            if (proofNode.right != null) {
                queue.pushBack(proofNode.right);
            }
        }
        System.out.println();

    }


    public void printLevelOrder() {
        levelOrder(this.root);
    }

    private Node insert(int data, Node root) {

        if (root == null) { // the root is null, then that means the tree is empty
            root = new Node(data);
            this.size++;
        } else if (data != root.data) { // if the new data isn't in the tree, then we can see where to insert it.
            if (data < root.data) { //peek right
                root.left = insert(data, root.left);
            } else { //peek left
                root.right = insert(data, root.right);
            }
        }

        return root;
    }

    public void insertData(int newData) {
        this.root = insert(newData, this.root);
    }

    //Find the minimum: we just go all the way down to the left, cause there is secure that a very little element will live.
    private Node findMinimum(Node root) {
        if (root != null) {
            while (root.left != null) {
                root = root.left;
            }
        }

        return root;
    }

    public int findMinorValue() { //driver function
        Node returnNode = findMinimum(this.root);
        return returnNode.data;
    }

    private Node findMaximum(Node root) { //we go down all the way to the right cause there we now it will exist
        //the greatest values of the tree.
        if (root != null) {
            while (root.right != null) {
                root = root.right;
            }
        }

        return root;
    }

    public int findMaxValue() { //driver function
        Node returnNode = findMaximum(this.root);
        return returnNode.data;
    }

    private Node remove(Node root, int data) {

        if (root != null) {
            if (data < root.data) { //if data is less than out current root.data we should remove the item on the left
                root.left = remove(root.left, data);
            } else if (data > root.data) { //if data is greater than our current root.data then we should remove item on the right
                root.right = remove(root.right, data);
            } else if (root.right == null && root.left == null) {
                //if we found a leaf, then we just should delete the leaf
                root = null;
            } else if (root.right == null) {
                //if our data is == to the current root.data and the current root doesn't has right subtree, then we
                //delete de current root by just pointing to the left.
                root = root.left;
            } else if (root.left == null) {
                //if our data is == to the current root.data and the current root doesn't has left subtree, then we
                //delete de current root by just pointing to the right.
                root = root.right;
            } else {
                //in case our current root has two sons, then we can get the maximum on the left subtree, or the minimum
                //right subtree the copy it to the current root.data we want to delete, and finally delete the duplicated
                //element we left in the remaining subtree.

                Node temporal = findMaximum(root.left);
                root.data = temporal.data;
                root.left = remove(root.left, root.data);
            }
        }

        this.size--;

        return root;
    }

    public void removeData(int data) { //driver function
        this.root = remove(this.root, data);
    }

    private boolean search(Node root, int data) {

        if (root != null) {
            if (data > root.data) {
                return search(root.right, data);
            } else if (data < root.data) {
                return search(root.left, data);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean find(int data) { //driver function
        return search(this.root, data);
    }

    private int height(Node root) {

        if (root == null) { //if the root is null, then the height is 0
            return 0;
        } else { //in other case if we got a node, then we count the node, and search the height in the other subtrees.
            return 1 + Math.max(height(root.left), height(root.right));
        }

    }

    public int getTreeHeight() { //driver function
        return height(this.root);
    }

    private int size(Node root) {
        //this works the same as the previous method were we got the height of the tree.
        if (root == null) {
            return 0;
        } else {
            return 1 + size(root.left) + size(root.right);
        }
    }

    public int getSizeRecursive() { //driver function
        return size(this.root);
    }

    public Node leftDescendant(Node root) { //returns the closest left descendant
        Node referenceNode = root.right;

        while (referenceNode.left != null) {
            referenceNode = referenceNode.left;
        }

        return referenceNode;
    }

    /*public Node rightAncestor(Node root){


    }*/


    public int getSize() {
        return size;
    }

    private Node levelOrderCreate(int[] array) { //this fills your tree in level order, given an array.

        Node node = new Node(array[0]);//root of our tree

        // queue for keeping track of the numbers in the array.
        LinkedListWithTail<Integer> numbers = new LinkedListWithTail<Integer>();
        // queue for filling the nodes' sons
        LinkedListWithTail<Node> treeCreator = new LinkedListWithTail<Node>();
        //first we set up the root of the tree, with the first element of the array.
        treeCreator.pushBack(node);
        //then we fill the queue with the numbers we want to have in the array.
        for (int i = 1; i < array.length; i++) {
            numbers.pushBack(array[i]);
        }

        //while we have numbers, we continue executing filling the tree
        while (!numbers.isEmpty()) {

            //whe point to the first node
            Node first = treeCreator.topFront();

            //we give him a left child and then we eliminate the first element in the number queue.
            if (first.left == null) {
                first.left = new Node(numbers.topFront());
                numbers.popFront();
            }
            //we give him a right child and then we eliminate the first element in the number queue.
            if (first.right == null) {
                try {
                    first.right = new Node(numbers.topFront());
                    numbers.popFront();
                } catch (Exception e) {
                    first.right = null;
                }

            }
            //then we enqueue our sons for giving them sons too.
            treeCreator.pushBack(first.left);
            treeCreator.pushBack(first.right);
            //we eliminate the first element in the node queue in order to continue filling our levels.
            treeCreator.popFront();

        }

        return node;


    }

    private int addArrayElements(int[] A, boolean isEven) {
        int sum = 0;

        if (isEven) { //We skip the second element in the array
            for (int i = 0; i < A.length; i++) { //we add only elements in even positions
                if (i % 2 == 0) {
                    sum += A[i];
                }
            }
        } else { //We skip the first element in the array
            for (int i = 0; i < A.length; i++) {//we add only elements in odd positions
                if (i % 2 != 0) {
                    sum += A[i];
                }
            }
        }

        return sum;
    }

    private int snake(Node root) {


        int iterationCounter = 0;
        int food = root.data;
        LinkedListWithTail<Node> nodeQueue = new LinkedListWithTail<Node>();

        nodeQueue.pushBack(root.left);
        nodeQueue.pushBack(root.right);

        while (!nodeQueue.isEmpty()) {
            int[] levelArray = new int[nodeQueue.getSize()];
            for (int i = 0; i < levelArray.length; i++) {
                Node n = nodeQueue.topFront();
                levelArray[i] = n.data;

                if (n.left != null) {
                    nodeQueue.pushBack(n.left);
                }

                if (n.right != null) {
                    nodeQueue.pushBack(n.right);
                }

                nodeQueue.popFront();
            }

            if (iterationCounter % 2 == 0) {
                food += addArrayElements(levelArray, true);
            } else {
                food += addArrayElements(levelArray, false);
            }

            iterationCounter++;

        }

        return food;


    }

    public int snakePathDriver() {
        return snake(this.root);
    }

    private String inOrderString(Node root) {

        String finalResult = "";
        if (root.left != null) {
            finalResult += inOrderString(root.left);
        }

        String result = root.data + " ";
        finalResult += result;

        if (root.right != null) {
            finalResult += inOrderString(root.right);
        }

        return finalResult;
    }

    public String inOrderStringDriver() {
        return inOrderString(this.root);
    }

    private String postOrderString(Node root) {

        String result = "";
        if (root.left != null) {
            result += postOrderString(root.left);
        }

        if (root.right != null) {
            result += postOrderString(root.right);
        }

        result += root.data + " ";

        return result;
    }

    public String postOrderStringDriver() {
        return postOrderString(this.root);
    }


    private String preOrderString(Node root) {

        String finalResult = root.data + " ";
        if (root.left != null) {
            finalResult += preOrderString(root.left);
        }

        if (root.right != null) {
            finalResult += preOrderString(root.right);
        }

        return finalResult;
    }


    public String preOrderStringDriver() {
        return preOrderString(this.root);
    }

    private int addArrayElements2(int[] A) {
        int sum = 0;

        for (int j : A) { //we add only elements in even positions
            sum += j;
        }

        return sum;
    }

    private int wisdomPath(Node root) { //so similar to the snake path exercise.

        int max = 0;
        LinkedListWithTail<Node> nodeQueue = new LinkedListWithTail<Node>();

        nodeQueue.pushBack(root);

        while (!nodeQueue.isEmpty()) {
            int[] levelArray = new int[nodeQueue.getSize()];
            for (int i = 0; i < levelArray.length; i++) {
                Node n = nodeQueue.topFront();
                levelArray[i] = n.data;

                if (n.left != null) {
                    nodeQueue.pushBack(n.left);
                }

                if (n.right != null) {
                    nodeQueue.pushBack(n.right);
                }

                nodeQueue.popFront();
            }

            int levelSum = addArrayElements2(levelArray);

            if (levelSum > max){
                max = levelSum;
            }

        }

        return max;

    }

    public int wisdomPathDriver (){
        return wisdomPath(this.root);
    }


}
