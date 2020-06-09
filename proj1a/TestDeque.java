public class TestDeque {
    public static void main(String[] args) {
        LinkedListDeque<String> l1 = new LinkedListDeque<>();
        boolean p1 = l1.isEmpty();
        LinkedListDeque<String> l2 = new LinkedListDeque<>("Hello");
        l2.addFirst("You");
        l2.addFirst("Say");
        l2.addLast("Wa");
//        boolean p2 = l2.isEmpty();
//        l2.printDeque();
//        String pop1 = l2.removeLast();
//        l2.printDeque();
//        String g1 = l2.get(100);
//        String g2 = l2.get(2);
//        String g3 = l2.getRecursive(2);
        LinkedListDeque<String> l3 = new LinkedListDeque<>(l2);
    }
}
