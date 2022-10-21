public class BST {
	class Node {
		
		// Fields
		private int key;
		private Node parent;
		private Node leftChild;
		private Node rightChild;
		
		// Constructors
		public Node() {
			key = -1;
			parent = null;
			leftChild = null;
			rightChild = null;
		}
		public Node(int key) {
			this.key = key;
			parent = null;
			leftChild = null;
			rightChild = null;
		}
		public Node(int key, Node parent, Node leftChild, Node rightChild) {
			this.key = key;
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		// Setter Methods
		public void setKey(int key) {
			this.key = key;
		}
		public void setParent(Node parent) {
			this.parent = parent;
		}
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		
		// Getter Methods
		public int getKey() {
			return key;
		}
		public Node getParent() {
			return parent;
		}
		public Node getLeftChild() {
			return leftChild;
		}
		public Node getRightChild() {
			return rightChild;
		}
		
	} // Node Class
	
	// Fields
	private Node root;
	private int counter;
	
	// Constructor
	public BST() {
		root = null;
		counter = 0;
	} // BST
	
	// Public Methods
	public int size() {
		return counter;
	} // size
	public void insert(int element) {
		if (counter == 0) {
			root = new Node(element);
			counter++;
		} else {
			Node current = root;
			boolean stillInserting = true;
			while (stillInserting) {
				if (element == current.key) {
					System.out.println("Element is already in the tree!");
					stillInserting = false;
				} else if (element < current.key) {
					if (current.leftChild != null) {
						current = current.leftChild;
					} else {
						current.setLeftChild(new Node(element, current, null, null));
						stillInserting = false;
						counter++;
					} // if - inserts new node as left child
				} else {
					if (current.rightChild != null) {
						current = current.rightChild;
					} else {
						current.setRightChild(new Node(element, current, null, null));
						stillInserting = false;
						counter++;
					} // if - inserts new node as right child
				} // if
			} // while
		} // if
	} // insert
	public void delete(int element) {
		Node current = root;
		boolean stillDeleting = true;
		while (stillDeleting) {
			if (element == current.key) {
				if (current.leftChild == null && current.rightChild == null) {
					if (current.parent != null) {
						current = current.parent;
						if (current.leftChild != null && current.leftChild.key == element) {
							current.setLeftChild(null);
						} else {
							current.setRightChild(null);
						} // if - leaf node
					} else {
						root = null;
					} // if - not root 
				} else if (current.leftChild == null ^ current.rightChild == null) {
					if (current.parent != null) {
						if (current.leftChild == null) { 
							if (current.parent.leftChild == current ) { // <
								current.parent.setLeftChild(current.rightChild);
								current.rightChild.setParent(current.parent);
							} else { // \
								current.parent.setRightChild(current.rightChild);
								current.rightChild.setParent(current.parent);
							} // if 
						} else { // /
							if (current.parent.leftChild == current ) {
								current.parent.setLeftChild(current.leftChild);
								current.leftChild.setParent(current.parent);

							} else { // >
								current.parent.setRightChild(current.leftChild);
								current.leftChild.setParent(current.parent);
							}
						} // if - one child
					} else {
						if (current.leftChild == null) {
							root = current.rightChild;
							current.rightChild.setParent(null);
							current.setRightChild(null);
						} else {
							root = current.leftChild;
							current.leftChild.setParent(null);
							current.setRightChild(null);
						}
					} // if - not root
				} else {
					Node max = current.leftChild;
					while (max.rightChild != null) {
						max = max.rightChild;
					} // while - two children - finding predecessor (rightmost leaf in left subtree)
					if (max != current.leftChild) {
						current.setKey(max.key);
						max = max.parent;
						max.setRightChild(null);
					} else {
						current.setKey(current.leftChild.key);
						if (max.leftChild != null || max.rightChild != null) {
							if (max.leftChild!= null) {
								current.setLeftChild(max.leftChild);
							} else {
								 current.setRightChild(max.rightChild);
							} // if - left child has children
						} else {
							current.setLeftChild(null);
						} // if - left child is leaf
					} // if 
				} // if
				stillDeleting = false;
				counter--;
			} else if (element < current.key) {
				if (current.leftChild != null) {
					current = current.leftChild;
				} else {
					System.out.println("Element not found!");
					stillDeleting = false;
				} // if
			} else {
				if (current.rightChild != null) {
					current = current.rightChild;
				} else {
					System.out.println("Element not found!");
					stillDeleting = false;
				} // if
			} // if 
		} // while 
	} // delete
	public void preorder() {
		preorderHelper(root);	
		System.out.println();
	} // preorder
	public void preorderHelper(Node current) {
		if (current == null) {
			return;
		} else {
			System.out.print(current.getKey() + " ");
			preorderHelper(current.leftChild);
			preorderHelper(current.rightChild);
		} // if
	} // preorderHelper
	public void postorder() {
		postorderHelper(root);
		System.out.println();
	} // postorder
	public void postorderHelper(Node current) {
		if (current == null) {
			return;
		} else {
			postorderHelper(current.leftChild);
			postorderHelper(current.rightChild);
			System.out.print(current.getKey() + " ");
		} // if
	} // postorderHelper
	public void inorder() {
		inorderHelper(root);
		System.out.println();
	} // inorder
	public void inorderHelper(Node current) {
		if (current == null) {
			return;
		} else {
			inorderHelper(current.leftChild);
			System.out.print(current.getKey() + " ");
			inorderHelper(current.rightChild);
		} // if
	} // inorderHelper
	
	public static void main(String[] args) {

		BST tree = new BST();

        tree.insert(4);

        tree.insert(5);

        tree.insert(2);

        tree.insert(9);

        tree.insert(6);

        tree.insert(11);

        tree.insert(10);

        tree.insert(8);

        tree.insert(1);

        tree.insert(14);
        
        tree.delete(1);
        tree.delete(2);
        tree.delete(4);
        tree.delete(5);
        tree.preorder();
        /**
        
        System.out.println("Example 1: preorder traversal");

        System.out.println("Expected:\t" + "4 2 1 5 9 6 8 11 10 14");

        System.out.print("Your Tree:\t");

        tree.preorder();

        System.out.println("\nExample 2: postorder traversal");

        System.out.println("Expected:\t" + "1 2 8 6 10 14 11 9 5 4");

        System.out.print("Your Tree:\t");

        tree.postorder();

        System.out.println("\nExample 3: inorder traversal");

        System.out.println("Expected:\t" + "1 2 4 5 6 8 9 10 11 14");

        System.out.print("Your Tree:\t");

        tree.inorder();
        

        tree.delete(1);

        System.out.println("\nExample 4: preorder traversal after a deleting a " + "leaf");

        System.out.println("Expected:\t" + "4 2 5 9 6 8 11 10 14");

        System.out.print("Your Tree:\t");

        tree.preorder();

        tree.delete(6);

        System.out.println("\nExample 5: preorder traversal after a deleting a " + "node with one child");

        System.out.println("Expected:\t" + "4 2 5 9 8 11 10 14");

        System.out.print("Your Tree:\t");

        tree.preorder();

        tree.delete(9);

        System.out.println("\nExample 6: preorder traversal after a deleting a " + "node with two children");

        System.out.println("Expected:\t" + "4 2 5 8 11 10 14");

        System.out.print("Your Tree:\t");

        tree.preorder();

        System.out.println("\nExample 7: size of the tree after insertions and " + "deletions");

        System.out.println("Expected:\t" + "7");

        System.out.println("Your Tree:\t" + tree.size());
        */

	} // main
} // BST
