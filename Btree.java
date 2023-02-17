
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

    public Node search(int key) {
        return this.find(root, key);
    }

    public void insert(Node node, int key) {
        if (node.isLeaf()) {
            if (node.canInsert()) {
                node.insertNonFull(node, key);
            } else {
                int i = 0;
                while (i >= 0 && node.getKeyByIndex(i) > key) {
                    i++;
                }
                Node tmp = node.getChildByIndex(i);
                splitNode(node, i, tmp);
            }

        } else {
            int i = 0;
            while (i >= 0 && node.getKeyByIndex(i) > key) {
                i++;
            }
            this.insert(node.getChildByIndex(i), key);
        }
    }

    public void splitNode(Node root, int index, Node nodeToSplit) {
        Node newNode = new Node(m, nodeToSplit.isLeaf());

        int mid = (int) Math.ceil((m - 1) / 2);
        for (int j = 0; j < mid; j++) {
            int val = nodeToSplit.getKeyByIndex(j + mid);
            nodeToSplit.setKeyByIndex(j + mid, 0);
            newNode.setKeyByIndex(j, val);
        }

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
            root.setChildByIndex(j + 1, tempNode);
        }

        root.setChildByIndex(index + 1, newNode);

        for (int j = m - 1; j >= index; j--) {
            int tempKey = root.getKeyByIndex(j);
            root.setKeyByIndex(j + 1, tempKey);
        }

        root.setKeyByIndex(index, mid);
    }
}