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

    public AVLTree() {
        this.root = null;
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

    private Node find(int key, Node root) {
        if (key == root.data) { //if the element is in the tree, we return the node that already contains the element.
            return root;
        } else {
            //if the key is less than the data then we search in the left only if there is a left subtree.
            if (key < root.data && root.left != null) {
                return find(key, root.left);
                //if the key is greater than the data then we search in the right only if there is a right subtree.
            } else if (key > root.data && root.right != null) {
                return find(key, root.right);
            } else {
                //at the end if the element is not in the tree, then we can return the node which should contain it
                //in case we try to insert the key in the tree.
                return root;
            }
        }
    }

    /*OPERATIONS FOR FINDING THE NEXT: */
    //Next takes an element and outputs an element with the next largest key

    //In this method we go all the way down through the left descendants until we find one that hasn't a left son.
    //Which means we have found the smallest descendant of the "rightSon" node that we took as a parameter.
    //Here we return the next bigger element than the root's key.
    private Node leftDescendant(Node rightSon) {

        Node referenceNode = rightSon.left;
        while (referenceNode != null) {
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
        if (root.right != null) {
            referenceNode = leftDescendant(referenceNode.right);
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

    //si el nuevo nodo es mayor que el dato de la raiz, se lo inserto a la derecha,
    //en otro caso se lo inserto a la derecha.

    //si elemento ya está, que no lo inserte

    private void insert(int key, Node root) {
        //first we search where the new element should be.
        Node referenceNode = find(key, root);

        if (key != referenceNode.data) { //if the key isn't in the tree, then we can insert it
            //if the key is greater than we insert to the right, in other case we insert to the left
            Node newNode = new Node(key);
            newNode.parent = referenceNode;

            if (key > referenceNode.data) {
                referenceNode.right = newNode;
            } else {
                referenceNode.left = newNode;
            }
        }

    }

    //Driver method.
    public void insertData(int key) {
        insert(key, this.root);
    }


    //Como eliminar
    //1. encontrar el nodo que queremos eliminar.
    //2. encontrar el next del nodo que queremos eliminar.
    //3. copiar el dato del next al nodo que queremos borrar.
    //4. asignar un padre a los hijos del nodo que se eliminó.
    //4.1 si el eliminado tenia hijo derecho, se promueve al lugar que estaba el eliminado
    //4.2 en caso contrario se le asigna al hijo izquierdo el padre el nodo eliminado.

    private void delete(int key, Node root) {

        //For deleteing a node, we must find it
        Node referenceNode = find(key, root);

        if (referenceNode.data == key) { //if the element is in the tree, then we can delete it
            if (referenceNode.right == null) { //if we don't have a right child, the is easy to delete
                referenceNode.parent.left = referenceNode.left; //we make the parent point to the left son of the deleted node
            } else {
                Node nextNode = next(referenceNode);
                referenceNode.data = nextNode.data;
                nextNode.parent.left = nextNode.right;
            }
        }

    }


}
