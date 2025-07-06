package test.Ejercicio_04_depth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.Ejercicio_04_depth.Depth;
import main.Materia.Models.Node;

import org.junit.jupiter.api.Test;

public class DepthTest {
    /// 4
    // 2 7
    // 1 3
    // 8
    @Test
    public void maxDepthTest() {
        Depth depth = new Depth();
        Node root = new Node(4);
        root.setLeft(new Node(2));
        root.setRight(new Node(7));
        root.getLeft().setLeft(new Node(1));
        root.getLeft().setRight(new Node(3));
        root.getLeft().getLeft().setLeft(new Node(8));

        int maxDepth = depth.Depth(root);
        assertEquals(4, maxDepth);
    }
}
