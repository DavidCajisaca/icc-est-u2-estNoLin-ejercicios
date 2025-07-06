package main.Ejercicio_03_listLeves;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import main.Materia.Models.Node;

public class ListLevels {
public List<List<Node>> listLevels(Node root) {
        List<List<Node>> result = new ArrayList<>();
        
        if (root == null) {
            return result; 
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Node> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                currentLevel.add(currentNode);
                if (currentNode.getLeft() != null) {
                    queue.add(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    queue.add(currentNode.getRight());
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public void printLevels(Node root) {
        List<List<Node>> levels = listLevels(root);

        int levelSpacing = 4; 
        for (int i = 0; i < levels.size(); i++) {
            List<Node> level = levels.get(i);

            StringBuilder levelOutput = new StringBuilder();
            int spaces = (int) Math.pow(2, levels.size() - i - 1) * levelSpacing; 

            for (Node node : level) {
                levelOutput.append(" ".repeat(spaces));
                levelOutput.append(node.getValue());
                levelOutput.append(" ".repeat(spaces));
            }

            System.out.println(levelOutput.toString().trim());
        }
    }

}
