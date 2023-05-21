package AVL;

import BST.BinarySearchTree;
import LinkedLIstWithTail.LinkedListWithTail;

public class AVLTree {

    private static class Node {

        private Node parent;
        private Node left;
        private Node right;

        private int height;
        private int data;

        public Node() {

        }

        public Node(int data) {

            this.parent = null;
            this.left = null;
            this.right = null;
            this.data = data;

        }
    }

    private Node root;
    private int size;

    public AVLTree() {
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
        LinkedListWithTail<Node> queue = new LinkedListWithTail<>();

        queue.pushBack(root);

        while (!queue.isEmpty()) {

            Node proofNode = queue.topFront();
            queue.popFront();
            System.out.print(proofNode.data + " ");

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
        System.out.println("Level order: ");
        levelOrder(this.root);
    }

    private Node find(int key, Node root) {

        try {
            if (key == root.data) { //if the element is in the tree, we return the node that already contains the element.
                return root;
            } else if (key < root.data && root.left != null) { //if the key is less than the data then we search in the left only if there is a left subtree.
                return find(key, root.left);
            } else if (key > root.data && root.right != null) { //if the key is greater than the data then we search in the right only if there is a right subtree.
                return find(key, root.right);
            } else {
                //at the end if the element is not in the tree, then we can return the node which should contain it
                //in case we try to insert the key in the tree.
                return root;
            }
        } catch (NullPointerException e) {
            //exception handling just for the first insertion.
            return null;
        }

    }

    /*OPERATIONS FOR FINDING THE NEXT: */
    //Next takes an element and outputs an element with the next largest key

    //In this method we go all the way down through the left descendants until we find one that hasn't a left son.
    //Which means we have found the smallest descendant of the "rightSon" node that we took as a parameter.
    //Here we return the next bigger element than the root's key.
    private Node leftDescendant(Node rightSon) {

        Node referenceNode = rightSon.left;
        while (referenceNode.left != null) {
            referenceNode = referenceNode.left;
        }

        return referenceNode;
    }

    //Here we handle the case in which we don't have a right child to find a successor. In this case the succesor will
    //be next bigger element than the root's key.
    private Node rightAncestor(Node root) {

        if (root != null) {
            Node referenceNode = root.parent;

            while (referenceNode.data < root.data) {
                referenceNode = referenceNode.parent;
            }

            return referenceNode;
        } else { //For handling the case in which there is no next greater element than the given node. i. e, our input
            //is already the greatest element in the tree.
            return null;
        }
    }

    //Final method for finding the succesor element.
    private Node next(Node root) {

        Node referenceNode = root;
        if (root.right != null && root.right.left != null) {
            referenceNode = leftDescendant(referenceNode.right);
        } else if (root.right != null) {
            referenceNode = root.right;
        } else {
            referenceNode = rightAncestor(referenceNode);
        }

        return referenceNode;
    }

    //Driver method:
    public Node findNext() {
        return next(this.root);
    }

//-------------------------------------------------------------------
    /*Implement range search (x, y, R)*/
    /*Implement nearest neighbors (x, y, R)*/
//-------------------------------------------------------------------


    private void insert(int key, Node root) {
        //first we search where the new element should be, for that we use a pointer called reference node.
        Node referenceNode = find(key, root); //this pointer is usefull for all the data, except the first root

        if (referenceNode != null) { // conditional for handling the first insertion.
            if (key != referenceNode.data) { //if the key isn't in the tree, then we can insert it

                Node newNode = new Node(key);
                newNode.parent = referenceNode;

                //if the key is greater than we insert to the right, in other case we insert to the left
                if (key > referenceNode.data) {
                    referenceNode.right = newNode;
                } else {
                    referenceNode.left = newNode;
                }

                referenceNode.height = height(referenceNode);

                this.size++;
            }

        } else {
            this.root = new Node(key); //this is how we insert the first node.
        }

    }

    //Driver method.
    public void insertData(int key) {
        insert(key, this.root);
    }

    private void delete(int key, Node root) {

        //For deleteing a node, we must find it
        Node referenceNode = find(key, root);

        if (referenceNode != null) {
            if (referenceNode.data == key) { //if the element is in the tree, then we can delete it

                if (referenceNode.left == null && referenceNode.right == null) { //if we want to delete a leaf.

                    if (referenceNode.data < referenceNode.parent.data) { //left leaf
                        referenceNode.parent.left = null;
                    } else { //right leaf
                        referenceNode.parent.right = null;
                    }

                } else if (referenceNode.left == null && referenceNode.data < referenceNode.parent.data) {
                    //left child without left child but a righ child
                    /*referenceNode.right != null*/ //not null is already checked up

                    referenceNode.parent.left = referenceNode.right;

                } else if (referenceNode.right == null && referenceNode.data > referenceNode.parent.data) {
                    //right child without right child but a left child

                    referenceNode.parent.right = referenceNode.left;

                } else if (referenceNode.data > referenceNode.parent.data && referenceNode.left == null) {
                    //right child without left child but a right child
                    referenceNode.parent.right = referenceNode.right;

                } else if (referenceNode.data < referenceNode.parent.data && referenceNode.right == null) {
                    //left child without right child but a left child
                    referenceNode.parent.left = referenceNode.left;

                } else {
                    //node with two sons
                    Node nextNode = next(referenceNode);
                    referenceNode.data = nextNode.data;
                    if (nextNode.parent == referenceNode) {
                        referenceNode.right = null;
                    } else {
                        nextNode.parent.left = nextNode.right;
                    }

                }
                this.size--;
            }
        } else {
            System.out.println("All the elements have been deleted.");
        }

    }

    //Driver method
    public void deleteData(int key) {
        delete(key, this.root);
    }

    private int height(Node root) {

        if (root == null) { // BinarySearchTree.java implementation
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }

    }

    public int getTreeHeight() { //driver function
        return height(this.root);
    }

    public int getSize() {
        return size;
    }


    //Rotations.
    private void rotateLeft(int key) {

        Node referenceNode = find(key, this.root);

        if (referenceNode != null && referenceNode.parent != null) {
            referenceNode.left.parent = referenceNode.parent;

            referenceNode.parent = referenceNode.parent.parent;

            referenceNode.parent.right = referenceNode;

            referenceNode.left.parent.right = referenceNode.right;

            referenceNode.left = referenceNode.left.parent;
        } else {

        }

    }

    private void rotateRight(Node referenceNode) {

        if (referenceNode != null) {
            if (referenceNode.left == null && referenceNode.right == null) { //for the leaves
                if (referenceNode.parent != null) {
                    Node parent = referenceNode.parent;

                    if (referenceNode.parent.parent != null) {
                        Node grandParent = referenceNode.parent.parent;
                        referenceNode.parent = grandParent;

                        referenceNode.right = parent;
                        grandParent.left = referenceNode;

                        parent.parent = referenceNode;
                        parent.left = null;

                    } else {
                        referenceNode.right = parent;
                        referenceNode.parent = null;

                        parent.parent = referenceNode;
                        parent.left = null;

                        this.root = referenceNode;
                    }

                }

            } else if (referenceNode.parent != null) {

                Node parent = referenceNode.parent;

                if (referenceNode.right != null){ //when our reference has a right child
                    Node rightChild = referenceNode.right;

                    if (referenceNode.parent.parent != null) { //when our reference's father isn't the root
                        Node grandParent = referenceNode.parent.parent;
                        rightChild.parent = parent;

                        referenceNode.parent = grandParent;
                        grandParent.left = referenceNode;

                        parent.left = rightChild;
                        referenceNode.right = parent;

                    } else { //when our reference's father is the root.
                        parent.parent = referenceNode;
                        referenceNode.parent = null;

                        parent.left = rightChild;
                        rightChild.parent = parent;

                        referenceNode.right = parent;
                        this.root = referenceNode;

                    }
                } else { //when our reference hasn't a right child
                    referenceNode.right = parent;
                    referenceNode.parent = parent.parent;
                    referenceNode.parent.left = referenceNode;
                    parent.left = null;

                }


            } else { //for the root

                if (referenceNode.left != null) {
                    referenceNode.parent = referenceNode.left;
                    if (referenceNode.left.right != null) { //when our left son has a sub tree.

                        Node rightChild = referenceNode.left.right;
                        referenceNode.parent.right = referenceNode;
                        referenceNode.left = rightChild;

                        referenceNode.parent.parent = null;


                    } else { //when our left son hasn't a sub tree.

                        referenceNode.left.right = referenceNode;
                        referenceNode.parent.parent = null;
                        referenceNode.left = null;
                    }
                    this.root = referenceNode.parent;

                }
            }
        }


            /*referenceNode.right.parent = referenceNode.parent;
            referenceNode.parent = referenceNode.parent.parent;

            if (referenceNode.parent.parent != null) {
                referenceNode.parent.left = referenceNode;

            }

            Node grandParent = referenceNode.parent.parent;
            Node parent = referenceNode.parent;
            Node rightChild = referenceNode.right;


            referenceNode.right.parent.left = referenceNode.right;
            referenceNode.right = referenceNode.right.parent;*/


    }

    public void rightRotation(int key) {
        Node referenceNode = find(key, this.root);
        rotateRight(referenceNode);
    }

    public void leftRotation() {

    }
}
