public class TestLinkedList {
    public static void main(String[] args){


        String v = new String("00");
        String w = new String("11");
        String wd = new String("22");
        String wdd = new String("33");
        String k = new String("k");





        LinkedList ll = new LinkedList(v);
        System.out.println("initial list : " + ll);

        ll.addHead(w);
        ll.addHead(wd);
        ll.addHead(wdd);
        System.out.println("mid list : " + ll + " of size : "+ ll.size());

        ll.insertHere(3, k);

        System.out.println("final list : " + ll + " of size : "+ ll.size());


    }
}
