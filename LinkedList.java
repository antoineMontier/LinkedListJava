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
     * destroy the tail, the cell before the tail become the new tail
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
            throw new IllegalArgumentException("bad index (" + pos + ") for the list " + this + " in the insertHere function");
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
     * @param pos is the position of the cell we need to remove ; 0 means you remove the head, size()-1 means you remove the tail and n means you remove the n cell 
     */
    public void removeCell(int pos){
        if(pos >= size() || pos < 0){
            throw new IllegalArgumentException("bad index (" + pos + ") for the list " + this + " in the remove function");
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
}
