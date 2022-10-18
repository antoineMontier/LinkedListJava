public class Cell{
    /**
     * object inside the cell
     */
    protected Object content;
    /**
     * pointeur on the next Cell
     */
    protected Cell next;

    /**
     * builder that makes an empty cell without the next cell
     */
    public Cell() {
        content = null;
        next = null;
    }

    /**
     * builder that makes a cell with an object inside of it, but there's not a following cell
     * @param _content object to put inside the cell
     */
    public Cell(Object o_content) {
        content = o_content;
        next = null;
    }

    /**
     * builder that males a new cell which will be the same as the parameter cell
     * @param cell is the cell that we want to copy
     */
    public Cell(Cell cell){
        content = cell.getcontent();
        next = cell.getnext();
    }

    /**
     * access to the next Cell
     * @return next
     */
    public Cell getnext() {
        return next;
    }

    /**
     * Set the next cell
     * @param c_next new next cell
     */
    public void setnext(Cell c_next) {
        next = c_next;
    }
    
    /**
     * set the next cell
     * @param  un object to put inside the next cell
     */
    public void setnext(Object o_next) {
        next = new Cell(o_next);
    }
    
    /**
     * access of the content of the current cell
     * @return content
     */
    public Object getcontent() {
        return content;
    }

    /**
     * set content
     * @param  le new content
     */
    public void setcontent(Object o_content) {
        content = o_content;
    }

    /**
     * representation 
     */
    public String toString() {
        if (content == null) {
            return "null";
        }
        else {
            return content.toString();
        }
    }

    /**
     * @param c is the cell we want to compare
     * @return  if the cells are exactly the same (same content and next cell)
     */
    public boolean equals(Cell c){
        return content == c.getcontent() && next == c.getnext();
    }
    

}