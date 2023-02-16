public class Node {
    static final int NOT_FOUND = -1;
    private int m;
    private int keys[];
    private Node child[];
    private boolean isLeaf;

    public Node(int _m) {
        this.m = _m;
        this.keys = new int[m - 1];
        this.child = new Node[m];
        this.isLeaf = true;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void setKeys(int[] _keys) {
        this.keys = _keys;
    }

    public void setLeaf(boolean v) {
        this.isLeaf = v;
    }

    public int getKeyByIndex(int index) {
        if (index > m - 1 || index < 0) {
            throw new IllegalArgumentException("index out of range !");
        } else {
            return this.keys[index];
        }
    }

    public int findKey(int key) {
        for (int i = 0; i < this.m - 1; i++) {
            if (this.keys[i] == key) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public Node getChild(int index) {
        if (index > -1 && index < this.m) {
            return this.child[index];
        }
        throw new IllegalArgumentException("index out of bound");
    }
}
