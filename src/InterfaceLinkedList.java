import java.util.ArrayList;

public interface InterfaceLinkedList {

    LinkedList addAZ(LinkedList ll);

    void addWord(String word, String txtFileName, LinkedList ll);

    void search(LinkedList ll, String key);

    public int totalWord(LinkedList ll);

    void mostTop10(LinkedList ll);

    void leastTop10(LinkedList ll);

    ArrayList<NodeLinkedList> createArrayList(LinkedList ll);

    ArrayList<NodeLinkedList> sortArrayList(ArrayList<NodeLinkedList> arrayList);

    void print(LinkedList ll);

    void top10Print(ArrayList<NodeLinkedList> arrayList);

    void errorMessage();

}