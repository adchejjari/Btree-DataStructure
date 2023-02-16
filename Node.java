public class Node {

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
        return -1;
    }
}
