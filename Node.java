import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Node {
    static final int NOT_FOUND = -1;
    static int empty = (int) Double.POSITIVE_INFINITY;
    private int m;
    public int keys[];
    public Node child[];
    private boolean isLeaf;

    public Node(int _m, boolean leaf) {
        this.m = _m;
        this.keys = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            keys[i] = empty;
        }
        this.child = new Node[m];
        this.isLeaf = leaf;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public void setKeys(int[] _keys) {
        this.keys = _keys;
    }
    
    public void setChildren(Node[] ch) {
    	this.child = ch;
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
        if (index >= m - 1 || index < 0) {
            throw new IllegalArgumentException("index out of range !");
        }
        this.keys[index] = key;
    }

    public Node getChildByIndex(int index) {
        if (index >= 0 && index < this.m) {
            return this.child[index];
        }
        throw new IllegalArgumentException("index out of bound");

    }

    public void setChildByIndex(int index, Node n) {
        if (index >= 0 && index < this.m) {
            child[index] = n;
            return;
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
            if (this.keys[i] == empty) {
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

    public void insertNonFull(int key) {

        for (int i = 0; i < m - 1; i++) {
            if (keys[i] == empty) {
                keys[i] = key;
                break;
            }
        }

        Arrays.sort(keys);
    }

    public void displayNode() {
        for (int i = 0; i < m - 1; i++) {
            if (keys[i] == empty) {
                System.out.print("0  ");
            } else {
                System.out.print(keys[i] + "  ");
            }

        }
        System.out.println(" ");
    }

}
