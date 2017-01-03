package collections.intervalTree;

/**
 * Class to represent intervals of comparable elements (i.e. values with natural
 * order). Interval objects are immutable.
 * 
 * @author Javier Iserte / Based on c# code of Interval-Tree from vvondra in
 *         github.
 *
 * @param <T>
 *          A comparable type.
 * @see <a href="https://github.com/vvondra/Interval-Tree">https://github.com/
 *      vvondra/Interval-Tree </a>
 */
public class Interval<T extends Comparable<T>>
    implements Comparable<Interval<T>> {

  // ------------------------------------------------------------------------ //
  // Instance variables
  /**
   * The starting value of the range. Start must be lower than end.
   */
  public final T start;
  /**
   * The starting value of the range. End must be larger than start.
   */
  public final T end;
  // ------------------------------------------------------------------------ //

  // ------------------------------------------------------------------------ //
  // Constructor
  /**
   * Creates a new Interval object from start and end values.
   * 
   * @param start
   *          the first value of the interval
   * @param end
   *          the last value of the interval
   */
  public Interval(T start, T end) {
    this.start = start;
    this.end = end;

    if (end.compareTo(start) <= 0) {
      throw new IllegalArgumentException("Start must be lower than end.");
    }

  }
  // ------------------------------------------------------------------------ //

  // ------------------------------------------------------------------------ //
  // Public Interface
  /**
   * Checks if the current interval contains completely another given interval.
   * 
   * @param interval
   * @return true if the start of the current interval is lower or equal to the
   *         start of the second interval and the end of the current interval is
   *         equal or larger than the end of the second interval.
   */
  public boolean contains(Interval<T> interval) {
    return this.start.compareTo(interval.start) <= 0
        && this.end.compareTo(interval.end) >= 0;
  }

  /**
   * Checks if the current interval contains a given value.
   * 
   * @param value
   * @return true if start <= value <= end, false otherwise
   */
  public boolean contains(T value) {
    return this.start.compareTo(value) <= 0 && this.end.compareTo(value) >= 0;
  }

  /**
   * Checks if the current interval overlaps with another interval.
   * 
   * @param interval
   * @return true if end2 >= start1 & end1 >= start2
   */
  public boolean overlaps(Interval<T> interval) {
    return this.end.compareTo(interval.start) >= 0
        && interval.end.compareTo(this.start) >= 0;
  }

  /**
   * Compares two intervals. Intervals are ordered by start, if starts are equal
   * end is used. If start and end are equal, the intervals are equal.
   */
  @Override
  public int compareTo(Interval<T> interval) {

    // Compares by start
    int compareStart = this.start.compareTo(interval.start);
    if (compareStart < 0) { return -1; } 
    if (compareStart > 0) { return 1;  }
    
    // If start are equal, compares by end
    int compareEnd = this.end.compareTo(interval.end);
    if (compareEnd < 0) { return 1; } 
    if (compareEnd > 0) { return -1; }

    // If start and end are equal, intervals are equal.
    return 0;
  }

  /**
   * 
   * @return a string representation of a Interval
   */
  public String toString() {
    return "[" + start + ", " + end + "]";
  }

  // ------------------------------------------------------------------------ //
}
