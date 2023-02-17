public class Main {

	public static void main(String[] args) {
		/*
		// Case 1
		System.out.println("-----CASE 1-----\n");
		int m = 14;
		Btree btree = new Btree(m);

		int[] keys = { 50, 55, 66, 68, 70, 71, 72, 73, 79, 81, 85, 90, 95 };
		for (int i = 0; i < keys.length; i++) {
			btree.root.setKeys(keys);
		}

		int[] searchKeys = { 10, 50, 25, 72, 95, 100 };
		for (int i = 0; i < searchKeys.length; i++) {
			int key = searchKeys[i];
			Node node = btree.search(key);
			if (node != null) {
				System.out.println("Key " + key + " found in the Btree");
			} else {
				System.out.println("Key " + key + " not found in the Btree");
			}
		}

		// Case 2
		System.out.println("\n-----CASE 2-----\n");
		int size = 5;
		int[] root = new int[size - 1];
		Btree btree2 = new Btree(5);
		for (int i = 0; i < size - 1; i++) {
			root[i] = i * 2;
			//System.out.println(root[i]);
		}
		btree2.root.setKeys(root);
		
		int[] searchKeys2 = { 0, 1, 2 };
		for (int i = 0; i < searchKeys2.length; i++) {
			int key2 = searchKeys2[i];
			Node node2 = btree2.search(key2);
			if (node2 != null) {
				System.out.println("Key " + key2 + " found in the Btree");
			} else {
				System.out.println("Key " + key2 + " not found in the Btree");
			}
		}
		
		*/
		// Case 1
		System.out.println("-----CASE 1-----\n");
		Btree btree = new Btree(5);

        btree.insert(1);
        btree.insert(30);
        btree.insert(20);
        btree.insert(111);
        btree.insert(25);
        btree.insert(9000);
        btree.insert(21);
        btree.insert(9001);
        btree.insert(23);
//        btree.insert(40);

		btree.root.displayNode();
        
        System.out.println("\n-----Btree-----\n");
        
        btree.root.displayNode();
        btree.root.child[0].displayNode();
        btree.root.child[1].displayNode();
        
        
        btree.printBTree(btree);
	}
}
