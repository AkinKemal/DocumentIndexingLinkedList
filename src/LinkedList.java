import java.util.ArrayList;

public class LinkedList implements InterfaceLinkedList {

    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public NodeLinkedList head;

    public LinkedList() {
        this.head = null;
    }

    @Override
    public LinkedList addAZ(LinkedList ll) {
        char charAZ = 'a';
        do {
            String tmp = String.valueOf(charAZ);
            NodeLinkedList elementAZ = new NodeLinkedList(tmp);
            if (ll.head == null) {
                ll.head = elementAZ;
            } else {
                NodeLinkedList walk = ll.head;
                while (walk.next != null) {
                    walk = walk.next;
                }
                walk.next = elementAZ;
            }
            charAZ++;
        } while (charAZ != 'z');
        return ll;
    }

    @Override
    public void addWord(String word, String txtFileName, LinkedList ll) {
        char temporaryChar = word.charAt(0);
        String temporaryString = String.valueOf(temporaryChar);
        NodeLinkedList walk = null;
        if (ll.head != null) {
            walk = ll.head;
        }
        while (walk.next != null) {
            if (temporaryString.equals(walk.word)) {
                break;
            }
            walk = walk.next;
        }
        NodeLinkedList temporaryNode = new NodeLinkedList(word);
        NodeLinkedList temporaryTXT = new NodeLinkedList(txtFileName);
        if (walk.down == null) {
            walk.down = temporaryNode;
            temporaryNode.down = temporaryTXT;
        } else {
            boolean boolWord = true;
            NodeLinkedList walkDown = walk.down;
            while (walkDown.next != null) {
                if (walkDown.word.equals(word)) {
                    boolWord = false;
                    break;
                }
                walkDown = walkDown.next;
            }
            if (boolWord && !walkDown.word.equals(word)) {
                walkDown.next = temporaryNode;
                temporaryNode.down = temporaryTXT;
            } else {
                walkDown.frequency = walkDown.frequency + 1;
                boolean boolTXT = true;
                NodeLinkedList walkDownDown = walkDown.down;
                while (walkDownDown.next != null) {
                    if (walkDownDown.word.equals(txtFileName)) {
                        boolTXT = false;
                        break;
                    }
                    walkDownDown = walkDownDown.next;
                }
                if (boolTXT && !walkDownDown.word.equals(txtFileName)) {
                    walkDownDown.next = temporaryTXT;
                } else {
                    walkDownDown.frequency = walkDownDown.frequency + 1;
                }
            }
        }
    }

    @Override
    public void search(LinkedList ll, String key) {
        if (ll.head == null) {
            errorMessage();
        } else {
            NodeLinkedList walk = ll.head;
            char temporaryChar = key.charAt(0);
            String temporaryString = String.valueOf(temporaryChar);
            while (walk.next != null) {
                if (temporaryString.equals(walk.word)) {
                    break;
                }
                walk = walk.next;
            }
            NodeLinkedList walkDown = walk.down;
            while (walkDown != null) {
                if (walkDown.word.equals(key)) {
                    break;
                }
                walkDown = walkDown.next;
            }
            if (walkDown == null) {
                System.out.println("EN: " + ANSI_RED + "WARNING! " + ANSI_RESET + "The entered word was not found.");
                System.out.println("TR: " + ANSI_RED + "UYARI! " + ANSI_RESET + "Girilen kelime bulunamadı.");
            } else {
                System.out.println(ANSI_GREEN + " ✅ " + ANSI_RESET);
                System.out.println(ANSI_GREEN + walkDown.word + ANSI_RESET);
                System.out.println(ANSI_BG_GREEN + "Frequency: " + ANSI_BOLD + walkDown.frequency + ANSI_RESET);
                NodeLinkedList walkDownDown = walkDown.down;
                while (walkDownDown != null) {
                    System.out.print(ANSI_GREEN + walkDownDown.word + ANSI_RESET + "( " + ANSI_BOLD + walkDownDown.frequency + ANSI_RESET + " ) ");
                    walkDownDown = walkDownDown.next;
                }
            }
            System.out.println();
        }
    }

    @Override
    public int totalWord(LinkedList ll) {
        int counter = 0;
        if (ll.head == null) {
            errorMessage();
            return -1;
        } else {
            NodeLinkedList walk = ll.head;
            NodeLinkedList walkDown = walk.down;
            while (walk.next != null) {
                while (walkDown != null) {
                    walkDown = walkDown.next;
                    counter++;
                }
                walk = walk.next;
                walkDown = walk.down;
            }
        }
        return counter;
    }

    @Override
    public void mostTop10(LinkedList ll) {
        ArrayList<NodeLinkedList> arrayList = new ArrayList<>();
        if (ll.head == null) {
            errorMessage();
        } else {
            NodeLinkedList walk = ll.head;
            NodeLinkedList walkDown = walk.down;
            arrayList = createArrayList(ll);
            while (walk.next != null) {
                while (walkDown != null) {
                    for (int i = 0; i < 10; i++) {
                        if (arrayList.get(i).frequency < walkDown.frequency) {
                            if (!arrayList.contains(walkDown)) {
                                arrayList.set(i, walkDown);
                            }
                        }
                    }
                    walkDown = walkDown.next;
                }
                walk = walk.next;
                walkDown = walk.down;
            }
        }
        arrayList = sortArrayList(arrayList);
        top10Print(arrayList);
    }

    @Override
    public void leastTop10(LinkedList ll) {
        ArrayList<NodeLinkedList> arrayList = new ArrayList<>();
        if (ll.head == null) {
            errorMessage();
        } else {
            NodeLinkedList walk = ll.head;
            NodeLinkedList walkDown = walk.down;
            arrayList = createArrayList(ll);
            while (walk.next != null) {
                while (walkDown != null) {
                    for (int i = 0; i < 10; i++) {
                        if (arrayList.get(i).frequency > walkDown.frequency) {
                            if (!arrayList.contains(walkDown)) {
                                arrayList.set(i, walkDown);
                            }
                        }
                    }
                    walkDown = walkDown.next;
                }
                walk = walk.next;
                walkDown = walk.down;
            }
        }
        arrayList = sortArrayList(arrayList);
        top10Print(arrayList);
    }

    @Override
    public ArrayList<NodeLinkedList> createArrayList(LinkedList ll) {
        ArrayList<NodeLinkedList> arrayList = new ArrayList<>();
        NodeLinkedList walk = ll.head;
        NodeLinkedList walkDown = walk.down;
        int counter = 0;
        while (counter < 10) {
            if (walkDown.next == null) {
                walkDown = walk.next.down;
                walk = walk.next;
            }
            arrayList.add(walkDown);
            walkDown = walkDown.next;
            counter++;
        }
        return arrayList;
    }

    @Override
    public ArrayList<NodeLinkedList> sortArrayList(ArrayList<NodeLinkedList> arrayList) {
        int sizeArray = arrayList.size();
        for (int i = 0; i < sizeArray - 1; i++) {
            for (int j = i + 1; j < sizeArray; j++) {
                if (arrayList.get(j).frequency > arrayList.get(i).frequency) {
                    NodeLinkedList temporary = arrayList.get(j);
                    arrayList.set(j, arrayList.get(i));
                    arrayList.set(i, temporary);
                }
            }
        }
        return arrayList;
    }

    @Override
    public void print(LinkedList ll) {
        if (ll.head == null) {
            errorMessage();
        } else {
            NodeLinkedList walk = ll.head;
            NodeLinkedList walkDown = walk.down;
            while (walk.next != null) {
                System.out.print(ANSI_BOLD + ANSI_BG_GREEN + walk.word + " -> " + ANSI_RESET);
                while (walkDown != null) {
                    System.out.print(ANSI_GREEN + walkDown.word + ANSI_RESET + "( " + ANSI_BOLD + walkDown.frequency + ANSI_RESET + " ) ");
                    walkDown = walkDown.next;
                }
                System.out.println();
                walk = walk.next;
                walkDown = walk.down;
            }
        }
    }

    @Override
    public void top10Print(ArrayList<NodeLinkedList> arrayList) {
        int counter = 1;
        for (NodeLinkedList i : arrayList) {
            System.out.print(ANSI_PURPLE + counter + ". " + i.word + " -> " + ANSI_RESET);
            System.out.print(ANSI_BOLD + i.frequency + ANSI_RESET + "\n");
            counter++;
        }
    }

    @Override
    public void errorMessage() {
        System.out.println("EN: " + ANSI_RED + "WARNING! " + ANSI_RESET + "A system error has been detected. Please try again.");
        System.out.println("TR: " + ANSI_RED + "UYARI! " + ANSI_RESET + "Bir sistem hatası algılandı. Lütfen tekrar deneyin.");
    }

}