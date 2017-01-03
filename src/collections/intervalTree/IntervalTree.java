package collections.intervalTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to represent a binary search tree of intervals. The structure of the
 * tree is based on a augmented red-black-tree.
 * 
 * @author Javier Iserte / Based on c# code of Interval-Tree from vvondra in
 *         github.
 *
 * @param <T>
 *          A comparable type.
 * @see <a href="https://github.com/vvondra/Interval-Tree">https://github.com/
 *      vvondra/Interval-Tree </a>
 */
public class IntervalTree<T extends Comparable<T>>
    implements Iterable<Interval<T>> {

  // ------------------------------------------------------------------------ //
  // Instance Variables
  /**
   * The root node of the tree.
   */
  public IntervalNode<T> root;
  // ------------------------------------------------------------------------ //

  // ------------------------------------------------------------------------ //
  // Constructor
  /**
   * Creates a new empty IntervalTree object.
   */
  public IntervalTree() {
    this.root = null;
  }
  // ------------------------------------------------------------------------ //

  // ------------------------------------------------------------------------ //
  // Public interface
  /**
   * Search and retrieves all intervals that contains a given value.
   * 
   * @param val
   * @return a list of intervals containinf val.
   */
  public List<Interval<T>> search(T val) {
    List<Interval<T>> result = new ArrayList<Interval<T>>();
    this.searchSubtree(this.root, val, result);
    return result;
  }

  /**
   * Search and retrieves all intervls that overlaps with a given interval.
   * 
   * @param i
   *          a inteval.
   * @return a list of intervals overlappig i.
   */
  public List<Interval<T>> overlaps(Interval<T> i) {
    List<Interval<T>> result = new ArrayList<Interval<T>>();
    this.searchSubtree(this.root, i, result);
    return result;
  }

  /**
   * Searchs for the interval that overlaps another given, that is closest to
   * the root of the tree searching by the left branch of the tree.
   * 
   * @param i
   * @return
   */
  public Interval<T> searchFirstOverlapping(Interval<T> i) {
    IntervalNode<T> node = this.root;

    while (node != null && !node.interval.overlaps(i)) {
      if (node.left != null && node.left.maxEnd.compareTo(i.start) >= 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    if (node == null) {
      return null;
    }

    return node.interval;
  }

  /**
   * Returns the number of different intervals in the tree.
   * @return
   */
  public int size() {
    if (this.root == null) {
      return 0;
    }

    return this.root.size();

  }

  /** 
   * Adds a new interval to the tree
   * @param interval the new interval to be added.
   */
  public void add(Interval<T> interval) {
    IntervalNode<T> node = new IntervalNode<T>(interval);
    if (this.root == null) {
      node.color = NodeColor.Black;
      this.root = node;
    } else {
      this.insertInterval(interval, this.root);
    }
  }

  /**
   * Returns an iterator for the intervals in the tree. 
   * Implementation of Iterable interface that allow use of for{} loops with
   * IntervalTree.
   * 
   * Not thread safe. 
   */
  @Override
  public Iterator<Interval<T>> iterator() {
    
    return new IntervalTreeIterator(this);
  }

  /**
   * Removes an interval from the tree
   * @param interval to be removed
   */
  public void remove(Interval<T> interval) {
    this.removeNode(this.findInterval(root, interval));
  }

  /**
   * Retrieves a sorted list of all intervals in the tree.
   * @return a list of intervals
   */
  public List<Interval<T>> getIntervals() {
    List<Interval<T>> intervals = new ArrayList<>();
    collectIntervals(this.root, intervals);
    return intervals;
  }


  // ------------------------------------------------------------------------ //
  // Private Methods
  

  private void collectIntervals(IntervalNode<T> node,
      List<Interval<T>> interavals) {
    if (node == null) {
      return;
    }
    this.collectIntervals(node.left, interavals);
    interavals.add(node.interval);
    this.collectIntervals(node.right, interavals);
  }
  
  
  private void searchSubtree(IntervalNode<T> node, Interval<T> i,
      List<Interval<T>> result) {
    if (node == null) {
      return;
    }

    if (node.left != null) {
      this.searchSubtree(node.left, i, result);
    }

    if (i.overlaps(node.interval)) {
      result.add(node.interval);
    }

    // Interval start is greater than largest endpoint in this subtree
    if (node.right != null && i.start.compareTo(node.maxEnd) <= 0) {
      this.searchSubtree(node.right, i, result);
    }
  }

  private IntervalNode<T> findInterval(IntervalNode<T> tree, Interval<T> i) {

    while (tree != null) {

      if (tree.interval.compareTo(i) > 0) {
        tree = tree.left;
        continue;
      }

      if (tree.interval.compareTo(i) < 0) {
        tree = tree.right;
        continue;
      }

      if (tree.interval.compareTo(i) == 0) {
        return tree;
      }
    }

    return null;

  }

  private void searchSubtree(IntervalNode<T> node, T val,
      List<Interval<T>> result) {
    if (node == null) {
      return;
    }

    if (val.compareTo(node.maxEnd) > 0) {
      return;
    }

    if (node.left != null) {
      this.searchSubtree(node.left, val, result);
    }

    if (node.interval.contains(val)) {
      result.add(node.interval);
    }

    if (val.compareTo(node.interval.start) < 0) {
      return;
    }

    if (node.right != null) {
      this.searchSubtree(node.right, val, result);
    }
  }

  private void insertInterval(Interval<T> interval,
      IntervalNode<T> currentNode) {
    IntervalNode<T> addedNode = null;
    if (interval.compareTo(currentNode.interval) < 0) {
      if (currentNode.left == null) {
        addedNode = new IntervalNode<T>(interval);
        addedNode.color = NodeColor.Red;
        currentNode.left = addedNode;
        addedNode.parent = currentNode;
      } else {
        this.insertInterval(interval, currentNode.left);
        return;
      }
    } else if (interval.compareTo(currentNode.interval) > 0) {
      if (currentNode.right == null) {
        addedNode = new IntervalNode<T>(interval);
        addedNode.color = NodeColor.Red;
        currentNode.right = addedNode;
        addedNode.parent = currentNode;
      } else {
        this.insertInterval(interval, currentNode.right);
        return;
      }
    } else {
      return;
    }

    addedNode.parent.recalculateMaxEnd();

    this.renewConstraintsAfterInsert(addedNode);

    this.root.color = NodeColor.Black;

  }

  private void renewConstraintsAfterInsert(IntervalNode<T> node) {
    if (node.parent == null) {
      return;
    }

    if (node.parent.color == NodeColor.Black) {
      return;
    }

    IntervalNode<T> uncle = node.uncle();

    if (uncle != null && uncle.color == NodeColor.Red) {
      node.parent.color = uncle.color = NodeColor.Black;

      IntervalNode<T> gparent = node.grandParent();
      if (gparent != null && !gparent.isRoot()) {
        gparent.color = NodeColor.Red;
        renewConstraintsAfterInsert(gparent);
      }
    } else {
      if (node.parentDirection() == NodeDirection.Left
          && node.parent.parentDirection() == NodeDirection.Right) {
        this.rotateLeft(node.parent);
        node = node.left;
      } else if (node.parentDirection() == NodeDirection.Right
          && node.parent.parentDirection() == NodeDirection.Left) {
        this.rotateRight(node.parent);
        node = node.right;
      }

      node.parent.color = NodeColor.Black;

      if (node.grandParent() == null) {
        return;
      }
      node.grandParent().color = NodeColor.Red;

      if (node.parentDirection() == NodeDirection.Right) {
        this.rotateRight(node.grandParent());
      } else {
        this.rotateLeft(node.grandParent());
      }
    }

  }

  private void removeNode(IntervalNode<T> node) {
    if (node == null) {
      return;
    }

    IntervalNode<T> temp = node;
    if (node.right != null && node.left != null) {

      temp = node.getSuccessor();
      node.interval = temp.interval;

      node.recalculateMaxEnd();
      while (node.parent != null) {
        node = node.parent;
        node.recalculateMaxEnd();
      }
    }
    node = temp;
    temp = node.left != null ? node.left : node.right;

    // we will replace node with temp and delete node
    if (temp != null) {
      temp.parent = node.parent;
    }
    if (node.isRoot()) {
      this.root = temp; // Set new root
    } else {

      // Reattach node to parent
      if (node.parentDirection() == NodeDirection.Right) {
        node.parent.left = temp;
      } else {
        node.parent.right = temp;
      }

      IntervalNode<T> maxAux = node.parent;
      maxAux.recalculateMaxEnd();
      while (maxAux.parent != null) {
        maxAux = maxAux.parent;
        maxAux.recalculateMaxEnd();
      }
    }

    if (node.color == NodeColor.Black && temp != null) {
      this.renewConstraintsAfterDelete(temp);
    }

  }

  private void renewConstraintsAfterDelete(IntervalNode<T> node) {

    while (node != this.root && node.color == NodeColor.Black) {
      if (node.parentDirection() == NodeDirection.Right) {
        IntervalNode<T> aux = node.parent.right;
        if (aux.color == NodeColor.Red) {
          aux.color = NodeColor.Black;
          node.parent.color = NodeColor.Red;
          this.rotateLeft(node.parent);
          aux = node.parent.right;
        }

        if (aux.left.color == NodeColor.Black
            && aux.right.color == NodeColor.Black) {
          aux.color = NodeColor.Red;
          node = node.parent;
        } else {
          if (aux.right.color == NodeColor.Black) {
            aux.left.color = NodeColor.Black;
            aux.color = NodeColor.Red;
            this.rotateRight(aux);
            aux = node.parent.right;
          }

          aux.color = node.parent.color;
          node.parent.color = NodeColor.Black;
          aux.right.color = NodeColor.Black;
          this.rotateLeft(node.parent);
          node = this.root;
        }
      } else {
        IntervalNode<T> aux = node.parent.left;
        if (aux.color == NodeColor.Red) {
          aux.color = NodeColor.Black;
          node.parent.color = NodeColor.Red;
          this.rotateRight(node.parent);
          aux = node.parent.left;
        }

        if (aux.left.color == NodeColor.Black
            && aux.right.color == NodeColor.Black) {
          aux.color = NodeColor.Red;
          node = node.parent;
        } else {
          if (aux.left.color == NodeColor.Black) {
            aux.right.color = NodeColor.Black;
            aux.color = NodeColor.Red;
            this.rotateLeft(aux);
            aux = node.parent.left;
          }

          aux.color = node.parent.color;
          node.parent.color = NodeColor.Black;
          aux.left.color = NodeColor.Black;
          this.rotateRight(node.parent);
          node = this.root;
        }
      }
    }

    node.color = NodeColor.Black;
  }

  private void rotateRight(IntervalNode<T> node) {
    IntervalNode<T> pivot = node.left;
    NodeDirection dir = node.parentDirection();
    IntervalNode<T> parent = node.parent;
    IntervalNode<T> tempTree = pivot.right;
    pivot.right = node;
    node.parent = pivot;
    node.left = tempTree;
    if (tempTree != null) {
      tempTree.parent = node;
    }

    if (dir == NodeDirection.Left) {
      parent.right = pivot;
    } else if (dir == NodeDirection.Right) {
      parent.left = pivot;
    } else {
      this.root = pivot;
    }

    pivot.parent = parent;

    pivot.recalculateMaxEnd();
    node.recalculateMaxEnd();

  }

  private void rotateLeft(IntervalNode<T> node) {
    IntervalNode<T> pivot = node.right;
    NodeDirection dir = node.parentDirection();
    IntervalNode<T> parent = node.parent;
    IntervalNode<T> tempTree = pivot.left;
    pivot.left = node;
    node.parent = pivot;
    node.right = tempTree;
    if (tempTree != null) {
      tempTree.parent = node;
    }

    if (dir == NodeDirection.Left) {
      parent.right = pivot;
    } else if (dir == NodeDirection.Right) {
      parent.left = pivot;
    } else {
      this.root = pivot;
    }

    pivot.parent = parent;

    pivot.recalculateMaxEnd();
    node.recalculateMaxEnd();
  }

  // ------------------------------------------------------------------------ //
  // Auxiliary Classes
  private final class IntervalTreeIterator implements Iterator<Interval<T>> {

    private IntervalNode<T> cursor;
    private Set<IntervalNode<T>> visited;

    public IntervalTreeIterator(IntervalTree<T> tree) {
      this.cursor = tree.root.leftMost();
      this.visited = new TreeSet<>();
      this.visited.add(this.cursor);
    }

    @Override
    public boolean hasNext() {
      if (this.cursor == null)
        return false;
      return true;
    }

    @Override
    public Interval<T> next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      Interval<T> current = cursor.interval;

      this.cursor = this.scanNext(this.cursor);

      return current;
    }

    private IntervalNode<T> scanNext(IntervalNode<T> node) {

      // 0 - Visited myself?
      if (this.visited.contains(node)) {

        // 0 - Visited myself? -> yes

        // 1 - Visited my right child?
        if (node.right == null || this.visited.contains(node.right)) {

          // 1 - Visited my right child? -> yes

          // Remove my child from visited set
          if (node.left != null)
            this.visited.remove(node.left);
          if (node.right != null)
            this.visited.remove(node.right);

          // Scan my parent for the next, if i have one.
          if (node.parent != null) {
            return this.scanNext(node.parent);
          } else {
            return null;
          }

        } else {
          // 1 - Visited my right child? -> no

          // Add my left most child of my right child to the cisited set and
          // return it as the next cursor pointer.
          IntervalNode<T> ml = node.right.leftMost();
          this.visited.add(ml);
          return ml;

        }

      } else {

        // 0 - Visited myself? -> No

        // Add myself to the visited set and return myself as the next cursor
        // pointer
        this.visited.add(node);
        return node;
      }
    }
  }
}
