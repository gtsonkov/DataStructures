import implementations.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> myList = new SinglyLinkedList<>();
        for (int i = 0; i <20 ; i++) {
            myList.addLast(""+i);
        }
        for (String part :myList){
            System.out.println(part);
        }
    }
}