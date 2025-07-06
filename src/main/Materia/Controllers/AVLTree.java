
package main.Materia.Controllers;

import main.Materia.Models.Node;

public class AVLTree {
    private Node root;

    // Obtener la altura de un nodo
    private int height(Node node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    // Rotación derecha
    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Imprimir información antes de la rotación
        System.out.println("Rotacion Der en nodo: " + y.getValue() + ", Balance: " + getBalance(y));

        // Realizar rotación
        x.setRight(y);
        y.setLeft(T2);

        // Actualizar alturas
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        // Imprimir información después de la rotación
        System.out.println("Nueva raiz despues de rotacion der: " + x.getValue());

        // Devolver nueva raíz
        return x;
    }

    // Rotación izquierda
    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Imprimir información antes de la rotación
        System.out.println("Rotacion Izq en nodo: " + x.getValue() + ", Balance: " + getBalance(x));

        // Realizar rotación
        y.setLeft(x);
        x.setRight(T2);

        // Actualizar alturas
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        // Imprimir información después de la rotación
        System.out.println("Nueva raiz despues de rotacion der: " + y.getValue());

        // Devolver nueva raíz
        return y;
    }

    // Obtener el factor de balanceo de un nodo
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    // Método para insertar un nodo y equilibrar el árbol
    public Node insert(Node node, int value) {
        // Realizar inserción normal de BST
        if (node == null) {
            Node newNode = new Node(value);
            newNode.setHeight(1);
            // Obtener el factor de balanceo de este ancestro nodo
            int balance = getBalance(newNode);
            // Imprimir información del nodo después de la inserción
            System.out.println("Nodo insertado: " + newNode.getValue() + ", Balance: " +
                    balance);
            return newNode;

        }

        if (value < node.getValue()) {

            node.setLeft(insert(node.getLeft(), value));

        } else if (value > node.getValue()) {

            node.setRight(insert(node.getRight(), value));

        } else // No se permiten valores duplicados en el árbol AVL
            return node;

        System.out.println("- Nodo actual: " + node.getValue());
        // Actualizar la altura de este ancestro nodo
        int altura = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(altura);
        System.out.println("\tAltura del nodo: " + node.getValue() + " es = " + altura);

        // Obtener el factor de balanceo de este ancestro nodo
        int balance = getBalance(node);
        // Imprimir información del nodo después de la inserción
        System.out.println("\tEquilibrio del node: " + node.getValue() + " = " + balance);

        // Si el nodo está desbalanceado, hay 4 casos

        // Caso izquierda-izquierda
        if (balance > 1 && value < node.getLeft().getValue()) {
            printTreeNode2(node, "", true);

            return rightRotate(node);
        }

        // Caso derecha-derecha
        if (balance < -1 && value > node.getRight().getValue()) {

            printTreeNode2(node, "", true);

            return leftRotate(node);
        }

        // Caso izquierda-derecha
        if (balance > 1 && value > node.getLeft().getValue()) {

            node.setLeft(leftRotate(node.getLeft()));
            printTreeNode2(node, "", true);
            return rightRotate(node);
        }

        // Caso derecha-izquierda
        if (balance < -1 && value < node.getRight().getValue()) {
            System.out.println("\tDerecha-Izquierda a nodo " + node.getValue());
            node.setRight(rightRotate(node.getRight()));
            printTreeNode2(node, "", true);

            return leftRotate(node);
        }

        // Devolver el nodo (sin cambios)

        return node;
    }

    // Método para imprimir el árbol en orden
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }

    // Método para imprimir el árbol después de cada inserción
    public void printTree() {
        printTreeNode2(root, "", true);
    }

    public void printTreeNode2(Node root, String prefix, boolean isLeft) {

        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.getValue());
            if (root.getLeft() != null || root.getRight() != null) {
                if (root.getLeft() != null) {
                    printTreeNode2(root.getLeft(), prefix + (isLeft ? "│   " : "    "), true);
                } else {
                    System.out.println(prefix + (isLeft ? "│   " : "    ") + "├── null");
                }
                if (root.getRight() != null) {
                    printTreeNode2(root.getRight(), prefix + (isLeft ? "│   " : "    "), false);
                } else {
                    System.out.println(prefix + (isLeft ? "│   " : "    ") + "└── null");
                }
            }
        }
    }

    // Método para iniciar la inserción desde la raíz
    public void insert(int value) {
        System.out.println("* Nodo a instertar: " + value);
        root = insert(root, value);
        printTree();
        System.out.println("\n-------\n");
    }

    // Método para iniciar el recorrido en orden desde la raíz
    public void inOrder() {
        inOrder(root);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Valores que provocan rotaciones
        int[] values = { 10, 20, 15, 24, 9, 8, 21, 23, 50, 25 };

        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("Inorder traversal of the constructed AVL tree is:");
        tree.inOrder();
    }

    public Node addNode(int i) {
        System.out.println("Nodo a insertar: " + i);
        root = insert(root, i);
        return root;
    }
}
