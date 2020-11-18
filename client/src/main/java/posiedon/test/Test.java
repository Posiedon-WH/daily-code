package posiedon.test;

import java.util.Stack;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/8/17 17:09
 */
public class Test{

    public class Node{
        private int val;
        private Node left;
        private Node right;

        public Node(int val){
            this.val=val;
        }
    }

    public static void scanNode(Node node){
        if(node==null){
            return;
        }

        Stack<Node> stack=new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if(pop.left!=null){
                stack.push(pop.left);
            }
            if(pop.right!=null){
                stack.push(pop.right);
            }

            System.out.println("值="+pop.val);
        }
    }

}
