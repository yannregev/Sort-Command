import java.util.Iterator;
import java.lang.reflect.Constructor;

public class BinaryTree<E extends Comparable<E>> implements BinaryTreeInterface<E> {

	private class Node {
		private Node left;
		private Node right;
		private Node parent;
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
	      		this(copyNode.left, copyNode.right, copyNode.parent, copyNode.data);
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
			if (data.compareTo(element) < 0) {
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
    		Node startNode;
    		Node next;
    		public BSTIterator(Node position) {
      			next = startNode = new Node(position);
    		}

    		@Override
    		public boolean hasNext() {
        		return (next != null);
    		}

    		@Override
		public E next() {
			if (!hasNext()) {
				return null;
			}
			Node temp = new Node(next);
			next = Successor(temp) == null ? null : new Node(Successor(temp));
			return temp.data;
		}

    		@Override
    		public void remove() {
        		//..
    		}
  	};

  	private Node root;

  	public BinaryTree() {
    		root = null;
  	}
  	public BinaryTree(BinaryTree<E> tree) {
    		copyTree(root, tree.root);
  	}

  	private void copyTree(Node root, Node other) {
    		root = new Node(other);
    		if(other.left != null) {
      			copyTree(root.left, other.left);
    		}
    		if(other.right != null) {
      			copyTree(root.right, other.right);
    		}
  	}

	public void add(E element) {
		if (root == null) {
			root = new Node(element);
			System.out.println(" added root ");
		} else {
			System.out.println(" adding other nodes ");
			root.insert(root, element);
		}
	}

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
  /*
  @postcondition
  A copy of the input node is added to the tree.
  */

 	public void remove(E element) {
		if (root == null) {
			return;
		}
		if (root.data == element) {
			Node temp = new Node();
			temp.left = root;
			root.remove(temp, element);
			root = temp.left;
		} else {
			root.remove(null, element);
		}
	}
  /*
  @postcondition
  The node with the input element is removed.
  */

	public E get(E element) {
		try {
			Class<?> clazz = element.getClass();
			Constructor<?> copyConst = clazz.getConstructor(clazz);
			if (clazz != null) {
				@SuppressWarnings("unchecked")
				E temp = (E) copyConst.newInstance(element);
				return temp;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
  /*
  @postcondition
  A copy of the element is returned if it exists. Otherwise null is returned.
  */

  	public Node treeMaximum(Node element) {
    		while(element.right != null) {
        		element = element.right;
    		}
    		return element;
  	}

  	public Node treeMinimum(Node element) {
    		while(element.left != null) {
        		element = element.left;
    		}
    		return element;
  	}

	public Node Successor(Node origin) {
		if(origin == null) {
	 		return null;
		}
		if(origin.right != null) {
			return treeMinimum(origin.right);
		}
		Node temp = origin.parent;
		while(temp != null && origin == temp.right ) {
			origin = temp;
			temp = temp.parent;
			if(temp.right == null) {
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
  	public Node Predecessor(Node origin) {
		if (origin.left != null) {
			return treeMaximum(origin.left);
		}
		Node temp = origin.parent;
		while (temp != null && origin == temp.left) {
			origin = temp;
			temp = temp.parent;
		}
	    	return temp;
	}
  /*
  @postcondition
  the node with the greatest key smaller than the key of input node is returned. 
  Inorder predecessor is NULL for the last node in Inoorder traversal.
  */
  	public Iterator<E> ascendingIterator() {
   		return new BSTIterator(treeMinimum(root));
  	}
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-decreasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/
/*
  public Iterator<E> descendingIterator() {
    return this.root;
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
