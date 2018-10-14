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
  public BinaryTreeInterface<E> add(E element);
  /*
  @postcondition
  A copy of the input node is added to the tree.
  */

  public BinaryTreeInterface<E> remove(E element);
  /*
  @postcondition
  The node with the input element is removed.
  */

  public boolean find(E element);
  /*
  @postcondition
  true  is returned if it exists. Otherwise false is returned.
  */

  public Iterator<E> ascendingIterator();
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-decreasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/

  public Iterator<E> descendingIterator();
  /**
  @postcondition
  The data stored in the binary search tree was iterated in
  monotonically non-increasing order and was added in this
  order to an object of the type Iterator<E>.
  This object of the type Iterator<E> was subsequently
  returned.
  **/

  public void printTree();
   /**
  @postcondition
  The tree is printed inorder.
  **/

  public BinaryTreeInterface<E> copy();
  /**
   * @precondition -
   * @postcondition A copy of the binary tree has been returned.
   */


}
