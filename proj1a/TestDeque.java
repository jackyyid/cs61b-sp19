import java.lang.reflect.Array;

public class TestDeque {
    public static void main(String[] args) {
        /*
        Test for LinkedListDeque.
         */
//        LinkedListDeque<String> l1 = new LinkedListDeque<>();
//        boolean p1 = l1.isEmpty();
//        LinkedListDeque<String> l2 = new LinkedListDeque<>("Hello");
//        l2.addFirst("You");
//        l2.addFirst("Say");
//        l2.addLast("Wa");
//        String r1 = l2.removeFirst();
//        boolean p2 = l2.isEmpty();
//        l2.printDeque();
//        String pop1 = l2.removeLast();
//        l2.printDeque();
//        String g1 = l2.get(100);
//        String g2 = l2.get(2);
//        String g3 = l2.getRecursive(2);
//        LinkedListDeque<String> l3 = new LinkedListDeque<>(l2);

        /*
        Test for ArrayDeque.
         */
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        boolean check1 = ad1.isEmpty();
        for (int i = 0; i < 50; i++) {
            ad1.addLast("a" + i);
        }
        for (int i = 0; i < 48; i++) {
            ad1.removeFirst();
        }
//        ad1.addLast("a");
//        boolean check2 = ad1.isEmpty();
//        int size1 = ad1.size();
//        ad1.printDeque();
//        String remove1 = ad1.removeFirst();
//        String remove2 = ad1.removeLast();
//        int a = 5 / 2;
//        String get1 = ad1.get(9);
//        String get2 = ad1.get(2);
//        String get3 = ad1.get(6);
//        ArrayDeque<String> ad2 = new ArrayDeque<>(ad1);
//        ad1.resize(16);

    }
}
