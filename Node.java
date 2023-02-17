import java.lang.Math;

public class Node {
    static final int NOT_FOUND = -1;
    private int m;
    private int keys[];
    private Node child[];
    private boolean isLeaf;

    public Node(int _m, boolean leaf) {
        this.m = _m;
        this.keys = new int[m - 1];
        this.child = new Node[m];
        this.isLeaf = leaf;
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

    public void setKeyByIndex(int index, int key) {
        if (index > m - 1 || index < 0) {
            throw new IllegalArgumentException("index out of range !");
        }
        this.keys[index] = key;
    }

    public Node getChildByIndex(int index) {
        if (index < 0 || index < this.m) {
            return this.child[index];
        }
        throw new IllegalArgumentException("index out of bound");

    }

    public void setChildByIndex(int index, Node n) {
        if (index < 0 || index < this.m) {
            child[index] = n;
        }
        throw new IllegalArgumentException("index out of bound");
    }

    public int findKey(int key) {
        for (int i = 0; i < this.m - 1; i++) {
            if (this.keys[i] == key) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public boolean canInsert() {
        for (int i = 0; i < m - 1; i++) {
            if (this.keys[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public void moveKeys(int index) {
        for (int i = m - 2; i > index; i--) {
            this.keys[i] = this.keys[i - 1];
        }
    }

    public void insertNonFull(Node node, int key) {
        int i = 0;
        while (i < m - 1 && key > node.getKeyByIndex(i))
            i++;
        moveKeys(i);
        this.keys[i] = key;
    }
}
