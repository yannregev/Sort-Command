import java.util.Iterator;

public class BinaryTree<E extends Comparable<E>> implements BinaryTreeInterface<E> {

  private class Node<E extends Comparable<E>> {
    private Node<E> left;
    private Node<E> right;
    private Node<E> parent;
    private E data;
    public Node(E d) {
      this(null, null, null, d);
    }
    public Node(Node<E> r, Node<E> l, Node<E> p, E k) {
      this.left = l;
      this.right = r; 
      this.parent = p;
      this.data = k;
    }
    public Node(Node<E> copyNode) {
      this.left = copyNode.left;
      this.right = copyNode.right; 
      this.parent = copyNode.parent;
      this.data = copyNode.data;
    }
    public int compareTo(E otherData) {
      return this.data.compareTo(otherData);
    } 
  }

  private Node<E> root;

  public BinaryTree() {
    root = null;
  }
  public BinaryTree(BinaryTree<E> tree) {
    copyTree(root, tree.root);
  }

  private void copyTree(Node<E> root, Node<E> other) {
    root = new Node<E>(other);
    if(other.left != null) {
      copyTree(root.left, other.left);
    }
    if(other.right != null) {
      copyTree(root.right, other.right);
    }
  }

  public void insert(E element) {
    insertRec(root, element);
  }

  private void insertRec(Node<E> n, E element) {
    if(n == null) {
      n = new Node<E>(element);
    }
    if(n.compareTo(element) > 0) {
      insertRec(n.left, element);
    }
    if(n.compareTo(element) <= 0) {
      insertRec(n.right, element);
    }
  }

  /*
  @postcondition
  A copy of the input node is added to the tree.
  */

  public void remove(E element) {

  }
  /*
  @postcondition
  The node with the input element is removed.
  */

  public E get(E element) {
    return element;
  }
  /*
  @postcondition
  A copy of the element is returned if it exists. Otherwise null is returned.
  */
  public E Successor(E element) {
    return element;
  }
  /*
  @postcondition
  the node with the smallest key greater than the key of input node is returned. 
  Inorder Successor is NULL for the last node in Inoorder traversal.
  */
  public E Predecessor(E element) {
    return element;
  }
  /*
  @postcondition
  the node with the greatest key smaller than the key of input node is returned. 
  Inorder predecessor is NULL for the last node in Inoorder traversal.
  */
  /*
  public Iterator<E> ascendingIterator() {
    return this.root;
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
