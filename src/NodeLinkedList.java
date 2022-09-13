public class NodeLinkedList {

    public String word;
    public int frequency;
    public NodeLinkedList next;
    public NodeLinkedList down;

    public NodeLinkedList(String word) {
        this.word = word;
        this.frequency = 1;
        this.next = null;
        this.down = null;
    }

}