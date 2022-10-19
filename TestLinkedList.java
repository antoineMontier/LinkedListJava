public class TestLinkedList {
    public static void main(String[] args){


        String v = new String("00");
        String w = new String("22");
        String wd = new String("33");
        String wdd = new String("44");
        String k = new String("11");

        String a = new String("aa");
        String b = new String("bb");
        String c = new String("cc");




        LinkedList ll = new LinkedList(v);

        ll.addHead(w);
        ll.addHead(wd);
        ll.addHead(wdd);
        ll.insertHere(3, k);

        LinkedList l2 = new LinkedList();

        l2.addTail(a);
        l2.addTail(b);
        l2.addTail(c);


        System.out.println("initial list ll: " + ll + " of size : "+ ll.size());

        ll.reverse();

        System.out.println("final list ll: " + ll + " of size : "+ ll.size());

//-----------------------------------------------------------------------------------------//

        System.out.println("initial list l2: " + l2 + " of size : "+ l2.size());

        System.out.println("ll merged with l2 : " + ll.merge(l2) + " of size : "+ ll.merge(l2).size());


    }
}
