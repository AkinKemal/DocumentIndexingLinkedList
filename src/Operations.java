import java.util.Scanner;

public class Operations {

    ReaderFile readerFile = new ReaderFile();
    LinkedList linkedList = new LinkedList();
    GetFileNameFilter getFileNameFilter = new GetFileNameFilter();
    Scanner scanner = new Scanner(System.in);
    int sizeAllDocs;

    public Operations() {

    }

    //createLinkedList
    public void createLinkedList(LinkedList ll) {
        int i = 0;
        int j = 0;
        String addString = "";
        sizeAllDocs = getFileNameFilter.findFiles("AllDocs");
        for (int k = 1; k <= sizeAllDocs; k++) {
            String temporaryString = readerFile.readerFile(k + ".txt");
            for (i = 0; i < temporaryString.length(); i++) {
                if (temporaryString.charAt(i) != ' ') {
                    break;
                }
            }
            for (j = i; j < temporaryString.length(); j++) {
                if (temporaryString.charAt(j) != ' ') {
                    addString += temporaryString.charAt(j);
                } else if (temporaryString.charAt(j) == ' ' && addString.length() != 0) {
                    linkedList.addWord(addString, k + ".txt", ll);
                    addString = "";
                } else if (j == (temporaryString.length() - 1) && addString.length() != 0) {
                    addString += temporaryString.charAt(j);
                    linkedList.addWord(addString, k + ".txt", ll);
                    addString = "";
                }
            }
        }
    }

    //search
    public void search(LinkedList ll) {
        System.out.print("Please enter the word you want to search: ");
        String key = scanner.next();
        linkedList.search(ll, key);
    }

    //totalWord
    public void totalWord(LinkedList ll) {
        int temporary;
        temporary = linkedList.totalWord(ll);
        System.out.println("Total Word Count: " + temporary);
    }

    //mostTop10
    public void mostTop10(LinkedList ll) {
        linkedList.mostTop10(ll);
    }

    //leastTop10
    public void leastTop10(LinkedList ll) {
        linkedList.leastTop10(ll);
    }

    //print
    public void print(LinkedList ll) {
        linkedList.print(ll);
    }

    //totalFile
    public void totalFile() {
        System.out.println("Total File Count: " + sizeAllDocs);
    }

}