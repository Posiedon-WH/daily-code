package demo.think.fanxing;

import java.awt.*;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/25 18:41
 */
public class LinkedStack<T> {

    private class Node {
        T item;
        Node next;

        public Node() {
            item = null;
            next = null;
        }

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }

    private Node top = new Node();

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop(){
        T result=top.item;
        if(!top.end()){
            top=top.next;
        }
        return result;
    }

}
