import java.util.Arrays;

public class Btree {
    Node root;
    int m;

    public Btree(int _m) {
        this.m = _m;
        root = new Node(_m, true);
    }

    private Node find(Node node, int key) {
        int i = 0;
        while (i < m - 1 && key > node.getKeyByIndex(i))
            i++;

        if (root.findKey(key) != Node.NOT_FOUND)
            return node;

        if (node.isLeaf())
            return null;

        return this.find(node.getChildByIndex(i), key);
    }

    private void insert(Node node, int key) {
        if (node.isLeaf()) {
            if (node.canInsert()) {
                node.insertNonFull(key);
            } else {
                int i = 0;
                Node currentRoot = node;
                Node newRoot = new Node(m, false);
                node = newRoot;
                node.setChildByIndex(i, currentRoot);
                splitNode(node, i, currentRoot, key);
            }

        } else {
            int i = 0;
            while (i >= 0 && node.getKeyByIndex(i) > key) {
                i++;
            }
            this.insert(node.getChildByIndex(i), key);
        }
    }

    private void splitNode(Node root, int index, Node nodeToSplit, int key) {
        Node newNode = new Node(m, nodeToSplit.isLeaf());

        int[] tempkeys = new int[m];
        for (int i = 0; i < m - 1; i++) {
            tempkeys[i] = nodeToSplit.getKeyByIndex(i);
        }
        tempkeys[m - 1] = key;
        Arrays.sort(tempkeys);

        System.out.println("biggest value is : " + tempkeys[m - 1]);

        int mid = (int) Math.ceil((m - 1) / 2);
        System.out.println("mid : " + tempkeys[mid]);
        for (int j = 0; j < mid; j++) {
            int val = tempkeys[j + mid + 1];
            nodeToSplit.setKeyByIndex(j + mid, Node.empty);
            newNode.setKeyByIndex(j, val);
        }

        nodeToSplit.displayNode();
        newNode.displayNode();

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
    }

    public Node search(int key) {
        return this.find(root, key);
    }

    public void insert(int key) {
        System.out.println("before " + key);
        this.root.displayNode();
        this.insert(root, key);
        System.out.println("after " + key);
        this.root.displayNode();
    }
}