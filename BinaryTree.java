import java.util.Iterator;

public class BinaryTree<E extends Comparable<E>> implements BinaryTreeInterface<E> {

	private class Node {
		private Node left,
			right,
			parent;
		private E data;

		public Node() {
			this(null, null, null, null);
		}

		public Node(E d) {
			this.left = null;
			this.right = null; 
			this.parent = null;
			this.data = d;
		}
	    	public Node(Node r, Node l, Node p, E k) {
			this.left = l;
		      	this.right = r; 
		      	this.parent = p;
		      	this.data = k;
		    }
		
	    	public Node(Node copyNode) {
	      		this.left = copyNode.left;
		      	this.right = copyNode.right; 
		      	this.parent = copyNode.parent;
		      	this.data = copyNode.data;
	    	}
	    
	    	public int compareTo(E otherData) {
	      		return this.data.compareTo(otherData);
	    	} 

		public void remove(Node parent, E element) {
			int compare = data.compareTo(element);
			if (compare > 0) {
				if (left != null) {
					left.remove(this, element);				
				}
			} else if (compare < 0) {
				if (right != null) {
					right.remove(this, element);
				}
			} else {
				if (left != null && right != null) {
					this.data = right.minData();
					right.remove(this, this.data);
				} else if (parent.left == this) {
					parent.left = (left != null) ? left : right;
				} else if (parent.right == this) {
					parent.right = (left != null) ? left : right;
				}
			}
		}
    
		public void insert(Node p, E element) {
			if (data.compareTo(element) <= 0) {
				if (this.right == null) {
		  			this.right = new Node(null, null, p, element);
				} else {
					right.insert(right ,element);
				}
			} else {
				if (this.left == null) {
		  			this.left = new Node(null, null, p, element);
				} else {
					left.insert(left, element);
				}
			} 
		}

		public E minData() {
			if (left == null) {
				return data;
			} else {
				return left.minData();
			}
		}
	};

  	private class BSTIterator implements Iterator<E> {
		boolean descending;
    		Node next;
    		public BSTIterator(Node position,boolean desc) {
      			this.next = new Node(position);
			this.descending = desc;
    		}

    		@Override
    		public boolean hasNext() {
        		return (next != null);
    		}

    		@Override
		public E next() {
			if (!descending) {
				if (!hasNext()) {
					return null;
				}
				Node temp = new Node(next);
				
				this.next = Successor(temp) == null ? null : new Node(Successor(temp));
				return temp.data;
			} else {
				if (!hasNext()) {
					return null;
				}
				Node temp = new Node(next);
				
				this.next = Predecessor(temp) == null ? null : new Node(Predecessor(temp));
				return temp.data;
			}
		}

  	};

  	private Node root;

  	public BinaryTree() {
    		this.root = null;
	}
	
	public init() {
		this.root = null;
	}

  	private Node copyTree(Node root) {
		if(root == null) return null;
		return new Node(root.right, root.left, root.parent, root.data);
  	}

	public BinaryTreeInterface<E> add(E element) {
		if (root == null) {
			root = new Node(element);
		} else {
			root.insert(root, element);
		}
		return this;
	}
	/*
	@postcondition
	A copy of the input node is added to the tree.
	*/

	public void printTree() {
		inorderTreeWalk(root);
	}

	private void inorderTreeWalk(Node x) {
		if(x != null) {
			inorderTreeWalk(x.left);
			System.out.println(x.data);
			inorderTreeWalk(x.right);
		}
	}

	public BinaryTreeInterface<E> copy() {
		BinaryTree<E> copyTree = new BinaryTree<E>();
		copyTree.root = copyTree(this.root);
		return copyTree;
	}

 	public BinaryTreeInterface<E> remove(E element) {
		if (root == null) {
			return this;
		}
		if (root.data == element) {
			Node temp = new Node();
			temp.left = root;
			root.remove(temp, element);
			root = temp.left;
		} else {
			root.remove(null, element);
		}
		return this;
	}
	/*
	@postcondition
	The node with the input element is removed.
	*/

	public boolean find(E element) {
		Node temp = root;
		while(temp != null) {
			if(temp.compareTo(element) == 0) {
				return true;
			} else if(temp.compareTo(element) < 0) {
				temp = temp.right;
			} else {
				temp = temp.left;
			}
		}
		return false;
	}
	/*
	@postcondition
	true  is returned if it exists. Otherwise false is returned.
	*/

  	private Node treeMaximum(Node element) {
    		while(element.right != null) {
        		element = element.right;
    		}
    		return element;
  	}

  	private Node treeMinimum(Node element) {
    		while(element.left != null) {
        		element = element.left;
    		}
    		return element;
  	}

	private Node Successor(Node origin) {
			if(origin == null) {
		 		return null;
			}
			if(origin.right != null) {
				return treeMinimum(origin.right);
			}
			Node temp = origin.parent;
			if(temp == null || temp.right == null) {
			 	return temp;
			}
			while(origin.data.equals(temp.right.data)) {
				origin = temp;
				temp = temp.parent;
				if(temp == null || temp.right == null) {
			 		return temp;
				}
			}
			return temp;
		}
  /*
  @postcondition
  the node with the smallest key greater than the key of input node is returned. 
  Inorder Successor is NULL for the last node in Inoorder traversal.
  */
  	private Node Predecessor(Node origin) {
		if (origin.left != null) {
			return treeMaximum(origin.left);
		}
		Node temp = origin.parent;
		if (temp == null || temp.left == null) {
			return temp;
		}
		while (origin.data.equals(temp.left.data)) {
			origin = temp;
			temp = temp.parent;
			if (temp == null || temp.left == null) {
				return temp;
			}
		}
	    	return temp;
	}
  /*
  @postcondition
  the node with the greatest key smaller than the key of input node is returned. 
  Inorder predecessor is NULL for the last node in Inoorder traversal.
  */
  	public Iterator<E> ascendingIterator() {
   		return new BSTIterator(treeMinimum(root), false);
  	}
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-decreasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/

	public Iterator<E> descendingIterator() {
		return new BSTIterator(treeMaximum(root), true);
	}
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-increasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/
}
