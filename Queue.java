public class Queue<Type> {
    public class Node {
        private Type item;
        private Node next;
    }
    private Node first, last;

    public void add(Type item) {
        if(last == null) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
        }
    }

    public Type pop() {
        Node toReturn = first;
        first = first.next;
        return toReturn.item;
    }
}