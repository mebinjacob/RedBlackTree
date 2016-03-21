import java.util.LinkedList;
import java.util.Queue;

/**
 * Red-Black Tree API
 *
 * @author mebin
 */
public class RedBlackTree {

    private Node root;
    private Node nil = new Node(0, 0);
    private Node previousSortedInput;

    public RedBlackTree() {
        nil.redColor = false;
        root = nil;
    }

    /**
     * Represents a single node in the RBT.
     */
    public class Node {
        private int val;
        private int count;
        private boolean redColor;
        private Node left;
        private Node right;
        private Node parent;

        Node(int val, int count) {
            this.val = val;
            this.count = count;
            this.redColor = true;
            this.right = nil;
            this.left = nil;
        }
    }

    /**
     * Print the total count for IDs between ID1 and ID2 inclusively. Note, ID1
     * ≤ ID2
     *
     * @param id1
     * @param id2
     */
    public long inRange(int id1, int id2) {
        return inRange(root, id1, id2);
    }

    private long inRange(Node root, int id1, int id2) {
        if (root == this.nil) {
            return 0;
        } else if (id1 <= root.val && id2 >= root.val) {
            return root.count + inRange(root.left, id1, id2) + inRange(root.right, id1, id2);
        } else if (id1 > root.val) {
            return inRange(root.right, id1, id2);
        } else {
            return inRange(root.left, id1, id2);
        }
    }

    /**
     * Print's the ID and the count of the event with the lowest ID that is
     * greater that theID. Print “0 0”, if there is no next ID.
     *
     * @param theID
     */

    public void next(int theID) {
        Node start = this.root;
        int id = 0;
        int count = 0;

        while (start != this.nil) {
            if (theID == start.val) {
                if (start.right != this.nil) {
                    Node min = minimumTree(start.right);
                    id = min.val;
                    count = min.count;
                }
                break;
            } else if (theID < start.val) {
                id = start.val;
                count = start.count;
                start = start.left;
            } else {
                start = start.right;
            }
        }
        System.out.println(id + " " + count);

    }


    /**
     * Print the ID and the count of the event with the greatest key that is
     * less that theID. Print “0 0”, if there is no previous ID.
     *
     * @param theID
     */
    public void previous(int theID) {
        Node start = this.root;
        int id = 0;
        int count = 0;

        while (start != this.nil) {
            if (theID == start.val) {
                if (start.left != this.nil) {
                    Node max = maximumTree(start.left);
                    id = max.val;
                    count = max.count;
                }
                break;
            } else if (theID < start.val) {
                start = start.left;
            } else {
                id = start.val;
                count = start.count;
                start = start.right;
            }
        }
        System.out.println(id + " " + count);
    }

    /**
     * Assumes input passed is larger than all values already present in the
     * rbt.
     *
     * @param val
     */
    public void sortedInsert(int val, int count) {
        Node x = new Node(val, count);
        if (root == this.nil) {
            root = x;
            root.redColor = false;
            root.parent = nil;
        } else {
            previousSortedInput.right = x;
            x.parent = previousSortedInput;
            insertFixup(x);
        }
        previousSortedInput = x;
    }

    public void insert(int val, int count) {
        Node y = findNodeOrParent(val);
        insert(val, count, y);
    }

    private void insert(int val, int count, Node y) {
        Node z = new Node(val, count);
        if (root != null) {
            z.parent = y;
        } else {
            root = z;
            root.redColor = false;
            z.parent = null;
        }


        if (y == this.nil) {
            this.root = z;
        } else if (z.val < y.val) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = this.nil;
        z.right = this.nil;
        z.redColor = true;
        if (z.parent != null)
            insertFixup(z);
        // return z;
    }

    /**
     * Increase the count of the event theID by m. If theID is not present,
     * insert it. Print the count of theID after the addition.
     *
     * @param theID
     * @param m
     */
    public void increaseOrInsert(int theID, int m) {
        Node nodeOrParent = this.findNodeOrParent(theID);
        int toPrint = m;
        if (nodeOrParent.val == theID) {
            // its the same node
            nodeOrParent.count += m;
            toPrint = nodeOrParent.count;

        } else {
            // its the parent
            insert(theID, m, nodeOrParent);
        }
        System.out.println(toPrint);
    }

    /**
     * Decrease the count of theID by m. If theID’s count becomes less than or
     * equal to 0, remove theID from the counter. Print the count of theID after
     * the deletion, or 0 if theID is removed or not present.
     *
     * @param theID
     * @param m
     * @return the count value if > 0 else 0.
     */
    public int decreaseOrDelete(int theID, int m) {
        Node nodeOrParent = this.findNodeOrParent(theID);
        if (nodeOrParent.val == theID) {// this is the node, and its present
            nodeOrParent.count -= m;
            if (nodeOrParent.count > 0) {
                return nodeOrParent.count;
            } else {
                // remove it from the tree
                delete(nodeOrParent);
            }
        }
        return 0;
    }

    public int count(int theID) {
        if (root == null)
            return 0;
        Node nodeOrParent = findNodeOrParent(theID);
        return nodeOrParent.val == theID ? nodeOrParent.count : 0;
    }

    private Node findNodeOrParent(int val) {
        Node y = this.nil;
        Node x = this.root;
        while (x != this.nil) {
            y = x;
            if (x.val == val) {
                return y; // the node is found
            } else if (val < x.val) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return y;
    }

    private void inorder_helper(Node root) {
        if (root == null)
            return;
        inorder_helper(root.left);
        System.out.print("The id is");
        System.out.println(root.val);
        //System.out.print("The count is");
        //System.out.println(root.count);
        System.out.print("The color is");
        System.out.println(root.redColor);
        inorder_helper(root.right);
    }

    public void levelOrder_helper(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int size = queue.size();
        while (!queue.isEmpty()) {
            if (size == 0) {
                size = queue.size();
                System.out.println();
                System.out.print("New Level ");
                Node rootNode = queue.peek();
            }
            size--;
            Node tempNode = queue.poll();
            if (tempNode.parent.left == tempNode) {
                System.out.print("left -- > " + tempNode.val + " with parent " + tempNode.parent.val);
            } else if (tempNode.parent.right == tempNode) {
                System.out.print(" right -- > " + tempNode.val + " with parent " + tempNode.parent.val);
            } else {
                System.out.print(" root -- > " + tempNode.val);
            }
            if (tempNode.left != this.nil)
                queue.add(tempNode.left);
            if (tempNode.right != this.nil)
                queue.add(tempNode.right);

        }
        System.out.println("End of level order print");
    }

    private void inOrder() {
        inorder_helper(root);
    }

    public void levelOrder() {
        levelOrder_helper(root);
    }

    private boolean isNilNode(Node n) {

        return (n == nil);


    }

    private void insertFixup(Node z) {
        while (z.parent.redColor) {
            Node y = null;
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right; //verified
                if (y.redColor) { // Case 1
                    z.parent.redColor = false; //verified
                    y.redColor = false; //verified
                    z.parent.parent.redColor = true; //verified..pushing up the problem
                    z = z.parent.parent; //hence pushed z
                } else {
                    if (z == z.parent.right) {// case 2
                        z = z.parent;//verified
                        leftRotate(z);
                    }
                    z.parent.redColor = false; //case 3
                    z.parent.parent.redColor = true; //verified
                    rightRotate(z.parent.parent); //verified
                }

            } else {
                y = z.parent.parent.left;
                if (y.redColor) {
                    z.parent.redColor = false;
                    y.redColor = false;
                    z.parent.parent.redColor = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.redColor = false;
                    z.parent.parent.redColor = true;
                    leftRotate(z.parent.parent);
                }

            }
        }
        this.root.redColor = false;
    }

    /**
     * Transplant node u with node v.
     */
    private void transplant(Node u, Node v) {
        if (u.parent == this.nil) {
            this.root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public void delete(Node z) {
        Node y = z;
        Node x = null;
        boolean yOriginalRedColor = y.redColor;
        if (z.left == this.nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == this.nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            // here it comes if both left and right child are present
            y = minimumTree(y.right);
            yOriginalRedColor = y.redColor;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.redColor = z.redColor;
        }
        if (yOriginalRedColor == false)
            deleteFixup(x);
    }

    private void deleteFixup(Node x) {
        while (x != this.root && x.redColor == false) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.redColor) { // case 1
                    w.redColor = false;
                    x.parent.redColor = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.redColor == false && w.right.redColor == false) { // case
                    // 2
                    w.redColor = true;
                    x = x.parent;
                } else if (w.right.redColor == false) { // case 3
                    w.left.redColor = false;
                    w.redColor = true;
                    rightRotate(w);
                    w = x.parent.right;
                }
                w.redColor = x.parent.redColor;
                x.parent.redColor = false;
                w.right.redColor = false;
                leftRotate(x.parent);
                x = this.root;
            } else {
                if (x == x.parent.right) {
                    Node w = x.parent.left;
                    if (w.redColor) {
                        w.redColor = false;
                        x.parent.redColor = true;
                        rightRotate(x.parent);
                        w = x.parent.left;
                    }
                    if (w.left.redColor == false && w.right.redColor == false) {
                        w.redColor = true;
                        x = x.parent;
                    } else if (w.left.redColor == false) {
                        w.right.redColor = false;
                        w.redColor = true;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.redColor = x.parent.redColor;
                    x.parent.redColor = false;
                    w.left.redColor = false;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }

        x.redColor = false;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != this.nil) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == this.nil) {
            this.root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != this.nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == this.nil) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private Node minimumTree(Node x) {
        if (isNilNode(x))
            return x;
        while (!isNilNode(x.left))
            x = x.left;
        return x;
    }

    private Node maximumTree(Node x) {
        if (isNilNode(x))
            return x;
        while (!isNilNode(x.right))
            x = x.right;
        return x;
    }
}
