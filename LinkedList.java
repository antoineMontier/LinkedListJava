import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList {

  /**
   * pointeur to the header cell of the list
   */
  protected Cell head;

  /**
   * pointeur to the last cell of the list
   */
  protected Cell tail;

  /**
   * create a LinkedList without any cell (even head and tail)
   */
  public LinkedList() {
    head = null;
    tail = null;
  }

  /**
   * create a LinkedList by copying the param list, same content but not the same
   * adress
   */
  public LinkedList(LinkedList l) {
    if (l.getHead() == null) {
      head = null;
      tail = null;
    } else if (l.getHead().getnext() == null) {
      head = new Cell(l.getHead());
      tail = new Cell(l.getHead());
    } else {
      head = new Cell(l.getHead());
      Cell walker1 = new Cell(head);
      Cell walker2 = new Cell(head.getnext());
      // walker1.setnext(walker2);

      while (walker2.getnext() != null) {
        walker1 = walker2;
        walker2 = walker2.getnext();
        Cell e = new Cell(walker2);
        walker1.setnext(walker2);
      }

      walker1.setnext(walker2);
      tail = walker2;
    }
  }

  /**
   * @param nhead is the head we want to set as the head of the new Linked List
   */
  public LinkedList(Cell nhead) {
    if (nhead == null) {// empty cell exception
      head = null;
      tail = null;
    } else if (nhead.getnext() == null) {// lonely cell exception
      head = nhead;
      tail = nhead;
    } else {
      Cell walker = new Cell(head);
      while (walker.getnext() != null) {
        walker = walker.getnext();
      }
      tail = walker;
    }
  }

  public LinkedList(Object nObjhead) {
    Cell h = new Cell(nObjhead);
    head = h;
    tail = h;
  }

  /**
   * @return the last cell
   */
  public Cell getTail() {
    return tail;
  }

  /**
   * @param otail is the object we want to insert at the end of the linked List
   */
  public void addTail(Object otail) {
    Cell t = new Cell(otail);
    if (isEmpty()) {
      tail = t;
      head = t;
      return;
    }
    tail.setnext(t);
    tail = t;
  }

  /**
   * @return the first cell
   */
  public Cell getHead() {
    return head;
  }

  /**
   * @param oHead is the object we want to insert at the end of the linked List
   */
  public void addHead(Object oHead) {
    Cell h = new Cell(oHead);
    if (isEmpty()) {
      tail = h;
      head = h;
      return;
    }
    h.setnext(head);
    head = h;
  }

  /**
   * @return if the linkedlist is empty or not (if the header cell exists or not)
   */
  public boolean isEmpty() {
    return head == null; // the list is empty if the header cell hasn't been defined
  }

  /**
   * remove the head if the linked list isn't empty
   */
  public void removeHead() {
    if (isEmpty()) {
      return;// no changes needed if the linkedlist is already empty
    }

    Cell temp = new Cell();// keep in memory the header cell
    temp.setnext(head);
    head = head.getnext(); // head become the next cell
    temp = temp.getnext();// temp become the old head cell
    temp = null;// destroy the header cell
  }

  /**
   * destroy the tail, the cell before the tail become the new tail // working
   * perfectly
   */
  public void removeTail() {
    if (isEmpty()) {
      return;// no changes needed if the linkedlist is already empty
    }

    if (tail.equals(head)) {// if the tail and the head are the same cell, the list is one element
      tail = null;
      head = null;
      return;
    }

    Cell temp = new Cell();// this cell will be used to navigate into the linkedlist
    temp.setnext(head);
    while (!temp.getnext().equals(tail)) {// while the temp cell's next isn't tail, temp become the next cell and so on
      temp = temp.getnext();
    } // at this point temp is the cell just before tail
    tail = null;// delete tail
    tail = temp;// set the new tail
  }

  public String toString() {
    String r = "["; // string that will be returned

    if (isEmpty()) {
      r += "empty]";
      return r;
    }

    if (tail.equals(head)) {// if the tail and the head are the same cell, the list is one element
      return r + head + "]";
    }

    if (head.getnext().equals(tail)) {// third exception before the loop
      return r + head + ", " + tail + "]";
    }

    Cell temp = new Cell();// this cell will be used to navigate into the linkedlist
    temp.setnext(head);
    temp = temp.getnext(); // temps is now the header cell

    while (!temp.getnext().equals(tail)) {//// while the temp cell's next isn't tail
      r += temp + ", ";
      temp = temp.getnext();
    }
    r += temp + ", " + tail + "]";
    return r;
  }

  /**
   * @return the size of the linkedlist (starting at 0)
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    }
    int count = 1;
    Cell temp = new Cell();
    temp.setnext(head);
    while (!temp.getnext().equals(tail)) {
      temp = temp.getnext();
      count++;
    }
    return count;
  }

  /**
   * @param pos position to insert : 0 means before head ; size() means after tail
   *            and n means between n-1 and n
   * @param o   object to insert
   */
  public void insertHere(int pos, Object o) {
    // 3 exceptions
    if (pos > size() || pos < 0) {
      throw new IllegalArgumentException(
          "bad index \"" + pos + "\" for the list " + this + " in the insertHere function");
    }
    if (pos == 0) {
      addHead(o);
      return;
    }
    if (pos == size()) {
      addTail(o);
      return;
    }

    // general case

    Cell temp = new Cell();
    temp.setnext(head);
    // temp = temp.getnext(); //temp is located in the head

    for (int i = 0; i < pos; i++) {
      temp = temp.getnext();
    }
    // here, temp is the cell just before the "pos" cell

    Cell newbie = new Cell(o);
    newbie.setnext(temp.getnext());
    temp.setnext(newbie);
    return;
  }

  /**
   * @param pos is the position of the cell we need to remove ; 0 means you remove
   *            the head, size()-1 means you remove the tail and n means you
   *            remove the n cell // working perfectly
   */
  public void removeCell(int pos) {
    if (pos >= size() || pos < 0) {
      throw new IllegalArgumentException("bad index \"" + pos + "\" for the list " + this + " in the remove function");
    }
    if (pos == 0) {
      removeHead();
      return;
    }
    if (pos == size() - 1) {
      removeTail();
      return;
    }

    Cell temp = new Cell();
    temp.setnext(head);

    for (int i = 0; i < pos; i++) {
      temp = temp.getnext();
    }
    // here, temp is the cell just before the "pos" cell

    Cell temp2 = new Cell();
    temp2.setnext(temp.getnext());// temp and temp2 are on the same cell here
    temp2 = temp2.getnext();// temp2 is on the cell we need to destroy
    temp.setnext(temp2.getnext());
    temp2 = null;
  }

  /**
   * @param n is the index of the first value we want to swap
   * @param m is the index of the second value we want to swap
   */
  public void swap(int n, int m) {

    // two exceptions to catch (if the index ar out of the bounds of the arraylist)

    if (n >= size() || n < 0) {
      throw new IllegalArgumentException("bad index \"" + n + "\" for the list " + this + " in the remove function");
    }
    if (m >= size() || m < 0) {
      throw new IllegalArgumentException("bad index \"" + m + "\" for the list " + this + " in the remove function");
    }

    // exception if the user wants to swap the same cell : do nothing

    if (n == m) {
      return;
    }

    // here, n and m are differents and are both linked to an element inside the
    // linkedlist

    Cell temp1 = new Cell();
    temp1.setnext(head);

    Cell temp2 = new Cell();
    temp2.setnext(head);

    for (int i = 0; i <= n; i++) {
      temp1 = temp1.getnext();
    }

    for (int i = 0; i <= m; i++) {
      temp2 = temp2.getnext();
    }

    // here, temp1 is on the n cell and temp2 is on the m cell

    Object buffer = temp1.getcontent();
    temp1.setcontent(temp2.getcontent());
    temp2.setcontent(buffer);

    buffer = null;
  }

  /**
   * reverse the order of the list, for example : [a, b, c] => [c, b, a]
   */
  public void reverse() {
    if (isEmpty() || size() == 1) {// no modifications to do if the list has 0 or 1 element
      return;
    }
    for (int i = 0; i < size() / 2; i++) {
      swap(i, size() - i - 1);
    }
  }

  public static void reverse(LinkedList l){
    if(l.size() < 2){
      return;
    }
    for(int i = 0; i < l.size() / 2; i++){
      l.swap(i, l.size() - i - 1);
    }
  }

  /**
   * @return the content of the cell number n
   */
  public Object get(int n) {
    if (n >= size() || n < 0) {
      throw new IllegalArgumentException("bad index \"" + n + "\" for the list " + this + " in the remove function");
    }
    Cell buffer = new Cell();
    buffer = head;
    for (int i = 0; i < n; i++) {
      buffer = buffer.getnext();
    }
    return buffer.getcontent();
  }

  /**
   * this method creates a new list which is the result of the merge of this and l
   * (in this precise order)
   * 
   * @param l is the list we want to merge
   */
  public LinkedList merge(LinkedList l) {

    if (isEmpty()) {// if this is empty, no changes to do on l with the same head as this
      return l;
    }
    if (l.isEmpty()) {// if l is empty, no chnages to do on this
      return this;
    }

    LinkedList res = new LinkedList();// create a new linkedlist (for the result)

    Cell temp = new Cell();
    temp.setnext(head);
    temp = temp.getnext();

    while (!temp.equals(tail)) {
      res.addTail(temp);
      temp = temp.getnext();
    }
    res.addTail(tail); // here, tail contains the linkedlist 'this' (from head to tail include)

    temp.setnext(l.getHead());
    temp = temp.getnext();// temp is the head of the second List

    while (!temp.equals(l.getTail())) {
      res.addTail(temp);
      temp = temp.getnext();
    }

    res.addTail(l.getTail());
    return res;
  }

  /**
   * @param x first num object to compare
   * @param y second num object to compare
   * @return -1 if x < y ; 0 if x == y ; 1 if x > y
   *         used to compare int and double or short and float AND strings between
   *         them
   */
  public static int compare(Object x, Object y) {
    // System.out.println("comparing "+ x + " and "+y);
    if (type(x) == 0 || type(y) == 0) {
      throw new IllegalArgumentException("the object " + x + " cannot be compared to " + y);
    }
    // System.out.println("same type");

    if (type(x) != type(y)) {
      return 2;// don't make an error if it's a string and a num, just a 2 to continue other
               // code using this methode
    }

    String xstr = x.toString();
    String ystr = y.toString();

    if (type(x) == 2 && type(y) == 2) {// if both the objects are string
      return stringCompare(xstr, ystr);
    }
    // System.out.println("not a string");

    final double PRECISION = 0.01;// will be used for the contains(obj x) function

    double dx = Double.valueOf(xstr).doubleValue();
    double dy = Double.valueOf(ystr).doubleValue();

    // System.out.println("comparing "+ dx + " and "+dy);

    if (dx > dy + PRECISION) {// both objects are numbers
      return 1;
    }
    if (dx < dy - PRECISION) {
      return -1;
    }
    return 0;
  }

  // This method compares two strings
  // lexicographically without using
  // library functions
  private static int stringCompare(String str1, String str2) {

    int l1 = str1.length();
    int l2 = str2.length();
    int lmin = Math.min(l1, l2);

    for (int i = 0; i < lmin; i++) {
      int str1_ch = (int) str1.charAt(i);
      int str2_ch = (int) str2.charAt(i);

      if (str1_ch != str2_ch) {
        return str1_ch - str2_ch;
      }
    }
    if (l1 != l2) {
      return l1 - l2;
    } else {
      return 0;
    }
  }

  /**
   * @return 1 if the object is a double ; 2 if the object is an Integer ; 3 if
   *         the object is a string ; 0 if it's not comparable
   */
  public static int type(Object o) {
    String x = o.getClass().getSimpleName();
    if (stringCompare(x, "Integer") == 0 || stringCompare(x, "Float") == 0 || stringCompare(x, "Double") == 0
        || stringCompare(x, "Short") == 0 || stringCompare(x, "Long") == 0 || stringCompare(x, "Byte") == 0) {
      return 1;
    }
    if (stringCompare(x, "String") == 0) {
      return 2;
    }
    return 0;
  }

  /**
   * sort the list with the numeric rule or the string rule, hybrid lists cannot
   * be sorted with this methode
   */
  public void sort() {

    if (size() < 2) {
      return;// nothing to do for an empty list or a 1-element list
    }

    // 3 cases : the list is full of numericals obejcts (float, int, double....) --
    // the list is full of string objects -- the list is composed of at least 1
    // non-comparable objects

    // first step : detection :
    Cell walker = new Cell();
    walker.setnext(head);
    walker = walker.getnext();// walker is the header Cell

    int type = type(head.getcontent());// type is initialized by header cell's type

    for (int i = 1; i < size(); i++) {
      if (type != type(walker.getcontent())) {
        throw new IllegalArgumentException(
            "the list " + this + " is not made of the same type of comparables objects !");
      }
      walker = walker.getnext();
    }

    // here, the list is full of comparable elements of the same comparable type
    if (type == 0) {
      return; // because the objects aren't comparable
    }
    if (type == 1 || type == 2) {
      // bubble sort for numerical values
      int n = size() - 2;
      int exchangeindex;
      while (n >= 0) {
        exchangeindex = 0;
        for (int j = 0; j <= n; j++) {
          if (compare(get(j), get(j + 1)) == 1) { // that means value at j is greater than value at j+1
            swap(j, j + 1);
            exchangeindex = j;
          }
        }
        n = exchangeindex - 1;
      }
      return;// the list is sorted
    }
  }

  /**
   * @param x the object we're searching fot
   * @return if the object is present
   *         doesn't work properly with integer :/
   */
  public boolean contains(Object x) {

    if (size() == 0) {
      return false;
    }
    Cell walker = new Cell(head);
    if (type(x) != 0) {// special case for string and numerical objects
      // System.out.println("searching for : " + x);
      for (int i = 0; i < size(); i++) {
        if (type(walker.getcontent()) != 0 && compare(x, walker.getcontent()) == 0) {
          return true;
        } else {
          // System.out.println(x + " != " + walker.getcontent());
        }
        walker = walker.getnext();
      }
    } else {
      for (int i = 0; i < size(); i++)
        if (x.equals(walker.getcontent())) {
          return true;
        }
    }
    return false;
  }

  public static boolean contains(LinkedList l, Object x){
    for(int i = 0; i < l.size(); i++){
      if(l.get(i) == x){
        return true;
      }
    }
    return false;
  }

  /**
   * @param o the object we're testing
   * @return 1 : byte // 2 : short // 3 : int // 4 : long // 5 : float // 6 :
   *         double // 0 : non-numeric
   */
  private int numtype(Object o) {
    String x = o.getClass().getSimpleName();

    if (stringCompare(x, "Integer") == 0) {
      return 3;
    }
    if (stringCompare(x, "Float") == 0) {
      return 5;
    }
    if (stringCompare(x, "Double") == 0) {
      return 6;
    }
    if (stringCompare(x, "Short") == 0) {
      return 2;
    }
    if (stringCompare(x, "Long") == 0) {
      return 4;
    }
    if (stringCompare(x, "Byte") == 0) {
      return 1;
    }
    return 0;
  }

  /**
   * @return table of the elements of the LinkedList
   *         not working as i want to on the numerical values :/
   */
  public Object[] toTable() {

    if (size() == 0) {
      return new Object[0];// empty exception
    }
    Object r[] = new Object[size()];
    for (int i = 0; i < size(); i++) {
      r[i] = get(i);
    }

    return r;

    // here, i wanted to make a special array for each type of data in order to
    // answer more precisely the founction needs
    /*
     * int m = 0;
     * for(int i = 0 ; i < size() ; i++){
     * if(numtype(get(i)) == 0){
     * m = 100;
     * i = size() - 1;
     * //exit the loop if one of the value isn't numeric
     * }
     * if(m < numtype(get(i))){
     * m = numtype(get(i));
     * }
     * }
     * // here m is the "largest" type of number in the table
     * // or 0 if the table need to be declared with Object type ;
     * 
     * if(m == 1){
     * byte r[] = new byte[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (byte)get(i);
     * }
     * return r;
     * }
     * if(m == 2){
     * short r[] = new short[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (short)get(i);
     * }
     * return r;
     * }
     * if(m == 3){
     * int r[] = new int[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (int)get(i);
     * }
     * return r;
     * }
     * if(m == 4){
     * long r[] = new long[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (long)get(i);
     * }
     * return r;
     * }
     * if(m == 5){
     * float r[] = new float[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (float)get(i);
     * }
     * return r;
     * }
     * if(m == 6){
     * double r[] = new double[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = (double)get(i);
     * }
     * return r;
     * }
     * Object r[] = new Object[size()];
     * for(int i = 0 ; i < size() ; i ++){
     * r[i] = get(i);
     * 
     * return r;
     */
  }

  /**
   * @return the max num value of an array (interpreted as string : for example
   *         the string "44" can be compared to the num 37.5)
   */
  public double max() {
    if (size() == 0) {
      return 0;
    }
    if (size() == 1) {
      return (double) get(0);
    }

    double d = -1874585985;// random number to know if d has been modified after initialisation

    for (int i = 0; i < size(); i++) { // initialization of d asap
      if (type(get(i)) == 1 && compare(get(i), d) == 1 || d == -1874585985) {
        String s = get(i).toString();
        d = Double.valueOf(s).doubleValue();
      }
    }

    if (d == -1874585985) {// if d hasn't been changed return 0...
      return 0;
    }

    return d;

  }

  /**
   * @return the max num value of an array (interpreted as string : for example
   *         the string "44" can be compared to the num 37.5)
   */
  public double min() {
    if (size() == 0) {
      return 0;
    }
    if (size() == 1) {
      return (double) get(0);
    }

    double d = -1874585985;// random number to know if d has been modified after initialisation

    for (int i = 0; i < size(); i++) { // initialization of d asap
      if (type(get(i)) == 1 && compare(get(i), d) == -1 || d == -1874585985) {
        String s = get(i).toString();
        d = Double.valueOf(s).doubleValue();
      }
    }
    if (d == -1874585985) {// if d hasn't been changed return 0...
      return 0;
    }
    return d;
  }

  public  LinkedList filter(Predicate<T> filter) {
    LinkedList filteredList = new LinkedList();
    for (int i = 0; i < size(); i++) {
      T element = get(i);
      if (filter.test(element)) {
        filteredList.pushTail(element);
      }
    }
    return filteredList;
  }

  public LinkedList map(Function<T, U> mapfunction){
    LinkedList mappedList = new LinkedList();
    for(int i = 0; i < size(); i++) {
      mappedList.addHead(mapfunction.apply(get(i)));
    }
    return mappedList;
  }

  public LinkedList takeWhile(Predicate<T> filter){
    LinkedList takelist = new LinkedList();
    for(int i = 0 ; i < size() && filter.test(get(i)); i++){
      takelist.addHead(get(i));
    }
    takelist.reverse();
    return takelist;
  }

  public LinkedList dropWhile(Predicate<T> filter){
    LinkedList droplist = new LinkedList();
    int i = 0;
    while(filter.test(get(i))){
      i++;
    }
    while(i < size()){
      droplist.addHead(get(i));
    }
    droplist.reverse();
    return droplist;
  }

  void clear(){
    head = null;
    tail = null;
  }

}
