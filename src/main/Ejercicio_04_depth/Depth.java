package main.Ejercicio_04_depth;

import main.Materia.Models.Node;

public class Depth {
public int Depth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = Depth(root.getLeft());  
        int rightDepth = Depth(root.getRight());  
        return Math.max(leftDepth, rightDepth) + 1;  
    }
}