public class Btree {
    Node root;
    int m;

    public Btree(int _m) {
        this.m = _m;
        root = new Node(_m);
    }
}