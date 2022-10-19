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
    public LinkedList(){
        head = null;
        tail = null;
    }


    /**
     * @param nhead is the head we want to set as the head of the new Linked List
     */
    public LinkedList(Cell nhead){
        head = nhead;
        tail = nhead;
    }

    public LinkedList(Object nObjhead){
        Cell h = new Cell(nObjhead);
        head = h;
        tail = h;
    }



   /**
     * @return the last cell
     */
     public Cell getTail(){
        return tail;
    }




    /**
     * @param otail is the object we want to insert at the end of the linked List
     */
    public void addTail(Object otail) {
        Cell t = new Cell(otail);
        if(isEmpty()){
          tail = t;
          head = t;
        }
        tail.setnext(t);
        tail = t;
    }


    /**
     * @return the first cell
     */
    public Cell getHead(){
        return head;
    }

    /**
     * @param oHead is the object we want to insert at the end of the linked List
     */
    public void addHead(Object oHead){
        Cell h = new Cell(oHead);
        if(isEmpty()){
          tail = h;
          head = h;
        }
        h.setnext(head);
        head = h;
    }

    /**
     * @return if the linkedlist is empty or not (if the header cell exists or not)
     */
    public boolean isEmpty(){
        return head == null; //the list is empty if the header cell isn't already define
    }

    /**
     * remove the head if the linked list isn't empty
     */
    public void removeHead(){
        if(isEmpty()){
            return;//no changes needed if the linkedlist is already empty
        }

        Cell temp = new Cell();//keep in memory the header cell
        temp.setnext(head);
        head = head.getnext(); //head become the next cell
        temp = temp.getnext();//temp become the old head cell
        temp = null;//destroy the header cell
    }

    /**
     * destroy the tail, the cell before the tail become the new tail // working perfectly
     */
    public void removeTail(){
        if(isEmpty()){
            return;//no changes needed if the linkedlist is already empty
        }


        if(tail.equals(head)){//if the tail and the head are the same cell, the list is one element
            tail = null;
            head = null;
            return;
        }


        Cell temp = new Cell();//this cell will be used to navigate into the linkedlist
        temp.setnext(head);
        while(!temp.getnext().equals(tail)){//while the temp cell's next isn't tail, temp become the next cell and so on
            temp = temp.getnext();
        }//at this point temp is the cell just before tail
        tail = null;//delete tail
        tail = temp;//set the new tail
    }


    public String toString(){
        String r = "["; //string that will be returned

        if(isEmpty()){
            r += "empty]";
            return r;
        }

        if(tail.equals(head)){//if the tail and the head are the same cell, the list is one element
            System.out.println("list has one element");
            return r + head + "]";
        }

        if(head.getnext().equals(tail)){
            System.out.println("list has two element");
            return r + head + ", " + tail + "]";
        }

        Cell temp = new Cell();//this cell will be used to navigate into the linkedlist
        temp.setnext(head);
        temp = temp.getnext(); // temps is now the header cell

        while(!temp.getnext().equals(tail)){////while the temp cell's next isn't tail
            r+= temp + ", ";
            temp = temp.getnext();
        }
        r += temp + ", "+ tail + "]";
        return r;
    }

    /**
     * @return the size of the linkedlist (starting at 0)
     */
    public int size(){
        if(isEmpty()){
            return 0;
        }
        int count = 1;
        Cell temp = new Cell();
        temp.setnext(head);
        while(!temp.getnext().equals(tail)){
            temp = temp.getnext();
            count++;
        }
        return count;
    }

    /**
     * @param pos position to insert : 0 means before head ; size() means after tail and n means between n-1 and n
     * @param o object to insert
     */
    public void insertHere(int pos, Object o){
        //3 exceptions
        if(pos > size() || pos < 0){
            throw new IllegalArgumentException("bad index \"" + pos + "\" for the list " + this + " in the insertHere function");
        }
        if(pos == 0){
            addHead(o);
            return;
        }
        if(pos == size()){
            addTail(o);
            return;
        }

        //general case

        Cell temp = new Cell();
        temp.setnext(head);
        //temp = temp.getnext(); //temp is located in the head

        for(int i = 0 ; i < pos; i++){
            temp = temp.getnext();
        }
        //here, temp is the cell just before the "pos" cell

        Cell newbie = new Cell(o);
        newbie.setnext(temp.getnext());
        temp.setnext(newbie);
        return;
    }


    /**
     * @param pos is the position of the cell we need to remove ; 0 means you remove the head, size()-1 means you remove the tail and n means you remove the n cell // working perfectly
     */
    public void removeCell(int pos){
        if(pos >= size() || pos < 0){
            throw new IllegalArgumentException("bad index \"" + pos + "\" for the list " + this + " in the remove function");
        }
        if(pos == 0){
            removeHead();
            return;
        }
        if(pos == size() - 1){
            removeTail();
            return;
        }

        Cell temp = new Cell();
        temp.setnext(head);

        for(int i = 0 ; i < pos; i++){
            temp = temp.getnext();
        }
        //here, temp is the cell just before the "pos" cell

        Cell temp2 = new Cell();
        temp2.setnext(temp.getnext());//temp and temp2 are on the same cell here
        temp2 = temp2.getnext();//temp2 is on the cell we need to destroy
        temp.setnext(temp2.getnext());
        temp2 = null;
    }

    /**
     * @param n is the index of the first value we want to swap
     * @param m is the index of the second value we want to swap
     */
    public void swap(int n, int m){

      //two exceptions to catch (if the index ar out of the bounds of the arraylist)

      if(n >= size() || n < 0){
          throw new IllegalArgumentException("bad index \"" + n + "\" for the list " + this + " in the remove function");
      }
      if(m >= size() || m < 0){
          throw new IllegalArgumentException("bad index \"" + m + "\" for the list " + this + " in the remove function");
      }

      //exception if the user wants to swap the same cell : do nothing

      if(n == m){
        return;
      }

      //here, n and m are differents and are both linked to an element inside the linkedlist

      Cell temp1 = new Cell();
      temp1.setnext(head);

      Cell temp2 = new Cell();
      temp2.setnext(head);

      for(int i = 0 ; i <= n ; i++){
        temp1 = temp1.getnext();
      }

      for(int i = 0 ; i <= m ; i++){
        temp2 = temp2.getnext();
      }

      //here, temp1 is on the n cell and temp2 is on the m cell

      Object buffer = temp1.getcontent();
      temp1.setcontent(temp2.getcontent());
      temp2.setcontent(buffer);

      buffer = null;
    }

    /**
     * reverse the order of the list, for example : [a, b, c] => [c, b, a]
     */
    public void reverse(){
      if(isEmpty() || size() == 1){//no modifications to do if the list has 0 or 1 element
        return;
      }
      for(int i = 0 ; i < size()/2  ; i++){
        swap(i, size()-i-1);
      }
    }



    /**
     * this method creates a new list which is the result of the merge of this and l (in this precise order)
     * @param l is the list we want to merge
     */
    public LinkedList merge(LinkedList l){

      if(isEmpty()){//if this is empty, no changes to do on l with the same head as this
        return l;
      }
      if(l.isEmpty()){//if l is empty, no chnages to do on this
        return this;
      }

      LinkedList res = new LinkedList();//create a new linkedlist (for the result)

      Cell temp = new Cell();
      temp.setnext(head);
      temp = temp.getnext();

      while(!temp.equals(tail)){
        res.addTail(temp);
        temp = temp.getnext();
      }
      res.addTail(tail); //here, tail contains the linkedlist 'this' (from head to tail include)

      temp.setnext(l.getHead());
      temp = temp.getnext();//temp is the head of the second List

      while(!temp.equals(l.getTail())){
        res.addTail(temp);
        temp = temp.getnext();
      }

      res.addTail(l.getTail());
      return res;
    }





















}
