
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
        System.out.println(node.isLeaf());
        if (node.isLeaf()) {
            if (node.canInsert()) {
                node.insertNonFull(key);
            } else {
                node.displayNode();
                int i = 0;
                Node currentRoot = node;
                Node newRoot = new Node(m, false);
                node = newRoot;

                newRoot.setChildByIndex(i, currentRoot);

                splitNode(newRoot, i, currentRoot);
            }

        } else {
            int i = 0;
            while (i >= 0 && node.getKeyByIndex(i) > key) {
                i++;
            }
            this.insert(node.getChildByIndex(i), key);
        }
    }

    private void splitNode(Node root, int index, Node nodeToSplit) {
        Node newNode = new Node(m, nodeToSplit.isLeaf());

        int mid = (int) Math.ceil((m - 1) / 2);
        System.out.println("mid : " + nodeToSplit.getKeyByIndex(mid));
        for (int j = 0; j < mid; j++) {
            int val = nodeToSplit.getKeyByIndex(j + mid);
            nodeToSplit.setKeyByIndex(j + mid, 0);
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

        for (int j = m - 1; j >= index + 1; j--) {
            Node tempNode = root.getChildByIndex(j);
            root.setChildByIndex(j, tempNode);
        }

        root.setChildByIndex(index + 1, newNode);

        for (int j = m - 1; j >= index; j--) {
            int tempKey = root.getKeyByIndex(j);
            root.setKeyByIndex(j, tempKey);
        }

        root.setKeyByIndex(index, mid);
    }

    public Node search(int key) {
        return this.find(root, key);
    }

    public void insert(int key) {
        this.insert(root, key);
    }
}