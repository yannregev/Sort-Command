import java.util.Iterator;

public class BinaryTree<E extends Comparable<E>> implements BinaryTreeInterface<E>, Iterable<E> {

	private class Node {
		private Node left,
			right,
			parent;
		private E data;

		public Node() {
			this(null, null, null, null);
		}

		public Node(E d) {
			this.left = this.right = this.parent = null;
			this.data = d;
		}
	    	public Node(Node r, Node l, Node p, E k) {
			this.left = l;
		      	this.right = r; 
		      	this.parent = p;
		      	this.data = k;
		    }
		
	    	public Node(Node copyNode) {
			this(copyNode.right, copyNode.left, copyNode.parent, copyNode.data);
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
					this.data = (treeMinimum(right) == null) ? null : treeMinimum(right).data;
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
	};

  	private class BSTIterator implements Iterator<E> {
		boolean descending;
    		Node next;
    		public BSTIterator(Node position, boolean desc) {
			
      			this.next = position == null ? null : new Node(position);
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
	
	public BinaryTree(E data) {
		this.root = new Node(null, null, null, data);
    	}
	
	public BinaryTreeInterface<E> init() {
		this.root = null;
		return this;
	}

	@Override
	public BinaryTreeInterface<E> add(E element) {
		if (root == null) {
			root = new Node(element);
		} else {
			root.insert(root, element);
		}
		return this;
	}

	@Override
	public BinaryTreeInterface<E> copy() {
		BinaryTree<E> copyTree = new BinaryTree<E>();
		copyTree.root = copyTree(this.root);
		return copyTree;
	}

	private Node copyTree(Node root) {
		if(root == null) {
			return null;
		}
		Node temp =  new Node(copyTree(root.right), copyTree(root.left), null, root.data);
		if(temp.left != null) {
			temp.left.parent = temp;
		}
		if(temp.right != null) { 
			temp.right.parent = temp;
		}
		return temp;
  	}

	@Override
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

	@Override
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

	@Override
	public E minNode() {
		return treeMinimum(this.root) == null ? null : treeMinimum(this.root).data;
	}

	@Override
	public E maxNode() {
		return treeMaximum(this.root) == null ? null : treeMaximum(this.root).data;
	}

  	private Node treeMaximum(Node element) {
		if (element == null) return null;
    		while(element.right != null) {
        		element = element.right;
    		}
    		return element;
  	}

  	private Node treeMinimum(Node element) {
		if (element == null) return null;
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
	@postcondition - the node with the smallest key greater than the key of input node is returned
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
	@postcondition - the node with the greatest key smaller than the key of input node is returned
	*/


	@Override
  	public Iterator<E> ascendingIterator() {
   		return new BSTIterator(treeMinimum(root), false);
  	}
	
	@Override
	public Iterator<E> descendingIterator() {
		return new BSTIterator(treeMaximum(root), true);
	}

	@Override
	public Iterator<E> iterator() {
		return ascendingIterator();
	}
	/*
	@postcondition - the ascending iterator is returned
	*/
}
