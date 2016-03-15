public class RedBlackTree {

	private Node root;
	private Node nil;

	private class Node {
		private int val;
		private boolean redColor;
		private Node left;
		private Node right;
		private Node parent;
	}

	public void insert(int val) {
		Node y = this.nil;
		Node x = this.root;
		while (x != this.nil) {
			y = x;
			if (val < x.val)
				x = x.left;
			else
				x = x.right;
		}
		Node z = new Node();
		z.val = val;
		z.parent = y;

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
		colorFixer(z);
	}
	
	private void colorFixer(Node z){
		while(z.parent.redColor){
			Node y = null;
			if(z.parent == z.parent.parent.left){
				y = z.parent.parent.right;
				if(y.redColor){
					z.parent.redColor = false;
					y.redColor = false;
					z.parent.parent.redColor = true;
					z = z.parent.parent;
				}
				else if(z == z.parent.right){
					z = z.parent;
					leftRotate(z);
				}
				z.parent.redColor = false;
				z.parent.parent.redColor = true;
				rightRotate(z);
			}
			else{
				
			}
		}
	}
}
