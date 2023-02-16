public class Main {

    public static void main(String[] args) {
        int size = 5;

        int[] root = new int[4];

        Btree btree = new Btree(5);

        for (int i = 0; i < size - 1; i++) {
            root[i] = i * 2;
        }

        btree.root.setKeys(root);

        System.out.println(btree.search(20));

    }

}
