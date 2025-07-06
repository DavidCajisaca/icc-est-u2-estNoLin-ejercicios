package main.Ejercicio_01_insert;

import main.Materia.Models.Node;

public class InsertBSTTest {
    private Node root;

    public void insert(int value) {
        System.out.println("Nodo a insertar: " + value);
        root = insert(root, value);
    }

    public Node getRoot() {
        return root;
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            Node newNode = new Node(value);
            newNode.setHeight(1);  
            return newNode;
        }
        if (value > root.getValue()) {
            root.setLeft(insert(root.getLeft(), value));
        } else if (value < root.getValue()) {
            root.setRight(insert(root.getRight(), value));
        }
        return root;
    }
}