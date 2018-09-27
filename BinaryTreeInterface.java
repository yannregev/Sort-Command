import java.util.Iterator;
public interface BinaryTreeInterface<E extends Comparable<E>> {
  /*
  Elements    : Elements of type E
  Structure   : Binary tree
  Domain      : 
  Constructors:	

  public BinaryTree();
    PRE -
    POST- A new binary tree object is created and the tree is empty


  public BinaryTree(BinaryTree b);
    PRE -
    POST- A new BinaryTree object is created
      and the value is the copy of the 'b' object

  public Init();
    PRE - 
    POST- the tree is reset and all the nodes are removed.

  */
  public void add(E element);
  /*
  @postcondition
  A copy of the input node is added to the tree.
  */

  public void remove(E element);
  /*
  @postcondition
  The node with the input element is removed.
  */

  public E get(E element);
  /*
  @postcondition
  A copy of the element is returned if it exists. Otherwise null is returned.
  */

  public E Successor(E element);
  /*
  @postcondition
  the node with the smallest key greater than the key of input node is returned. 
  Inorder Successor is NULL for the last node in Inoorder traversal.
  */

  public E Predecessor(E element);
  /*
  @postcondition
  the node with the greatest key smaller than the key of input node is returned. 
  Inorder predecessor is NULL for the last node in Inoorder traversal.
  */
/*
  public Iterator<E> ascendingIterator();
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-decreasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/
/*
  public Iterator<E> descendingIterator();
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-increasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/
}
