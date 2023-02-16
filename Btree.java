public class Btree {
    Node root;
    int m;

    public Btree(int _m) {
        this.m = _m;
        root = new Node(_m);
    }

    private Node find(Node node, int key) {
        int i = 0;
        while (i < m - 1 && key > node.getKeyByIndex(i))
            i++;

        if (root.findKey(key) != Node.NOT_FOUND)
            return node;

        if (node.isLeaf())
            return null;

        return this.find(node.getChild(i), key);
    }

    public Node search(int key) {
        return this.find(root, key);
    }
}