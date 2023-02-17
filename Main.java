public class Main {

    public static void main(String[] args) {

        Btree btree = new Btree(5);

        btree.insert(1);

        btree.insert(30);

        btree.insert(20);

        btree.insert(111);

        btree.insert(25);

        btree.insert(9000);

        System.out.println("----------");

        btree.root.displayNode();
    }

}
