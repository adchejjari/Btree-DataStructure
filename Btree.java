import java.util.Arrays;

public class Btree {
	Node root;
	static int m;

	public Btree(int _m) {
		this.m = _m;
		root = new Node(_m, true);
	}

	private Node find(Node node, int key) {
		int i = 0;
		while (i < m - 1 && key > node.getKeyByIndex(i)) {
			i++;
		}
		System.out.println("child ! " + i);

		if (node.findKey(key) != Node.NOT_FOUND)
			return node;

		if (node.isLeaf())
			return null;
		System.out.println("searched item : " + key + " child" + i);
		node.getChildByIndex(i).displayNode();
		return this.find(node.getChildByIndex(i), key);
	}

	private void insert(Node parent, Node node, int key) {
		if (node.isLeaf()) {
			if (node.canInsert()) {
				node.insertNonFull(key);
			} else {
				int i = 0;
//				while (i < m - 1  && node.getKeyByIndex(i) < key && node.getKeyByIndex(i)!=Node.empty ) {
//					i++;
//				}
				Node currentRoot = node;
				
				Node newRoot = new Node(m, false);
				node = newRoot;
				
				
				node.setChildByIndex(i, currentRoot);
//				node.setLeaf(false);
//				System.out.println("is leaf ?" + node.getKeyByIndex(0));
				this.root = splitNode(node, i, currentRoot, key);
				this.root.setLeaf(false);
//				System.out.print("root in current state");
//				node.displayNode();
			}

		} else {
			int i = 0;
			while (i >= 0 && node.getKeyByIndex(i) < key && node.getKeyByIndex(i) != Node.empty) {
				i++;
			}
			this.insert(node, node.getChildByIndex(i), key);
		}
	}

	private Node splitNode(Node root, int index, Node nodeToSplit, int key) {
		root.setLeaf(false);
		Node newNode = new Node(m, nodeToSplit.isLeaf());
		int[] tempkeys = new int[m];
		for (int i = 0; i < m - 1; i++) {
			tempkeys[i] = nodeToSplit.getKeyByIndex(i);
		}
		tempkeys[m - 1] = key;
		Arrays.sort(tempkeys);

		int mid = (int) Math.ceil((m - 1) / 2);
		System.out.println("mid : " + tempkeys[mid]);
		for (int j = 0; j < mid; j++) {
			int val = tempkeys[j + mid + 1];
			nodeToSplit.setKeyByIndex(j + mid, Node.empty);
			newNode.setKeyByIndex(j, val);
		}

		nodeToSplit.displayNode();
		root.displayNode();

		if (!nodeToSplit.isLeaf()) {
			for (int j = 0; j < mid; j++) {
				Node tempNode = nodeToSplit.getChildByIndex(index);
				newNode.setChildByIndex(j, tempNode);
			}
			for (int k = mid; k < m; k++) {
				nodeToSplit.setChildByIndex(k, null);
			}
		}

		for (int j = m - 2; j >= index + 1; j--) {
			Node tempNode = root.getChildByIndex(j);
			root.setChildByIndex(j + 1, tempNode);
		}

		root.setChildByIndex(index + 1, newNode);

		for (int j = m - 3; j >= index; j--) {
			int tempKey = root.getKeyByIndex(j);
			root.setKeyByIndex(j + 1, tempKey);
		}

		root.setKeyByIndex(index, tempkeys[mid]);
		System.out.print("inside split method");
		root.displayNode();
		
		return root;
	}

	public Node search(int key) {
		return this.find(root, key);
	}

	public void insert(int key) {
		if(key==25) {
			System.out.println("before " + key);
			this.root.displayNode();
			
		}
		
		this.insert(null, this.root, key);
		if(key==25) {
			System.out.println("after " + key);
			this.root.displayNode();
			
		}
	}

	public void printBTree(Btree btree) {
		printBTreeNode(btree.root, 0);
	}

	private static void printBTreeNode(Node node, int level) {
		if (node != null) {
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			node.displayNode();
			if (!node.isLeaf()) {
				for (int i = 0; i <= m; i++) {
					//printBTreeNode(node.getChildByIndex(i), level + 1);
				}
			}
		}
	}
}
