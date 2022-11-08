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

        //byte x = 4;



        LinkedList ll = new LinkedList(v);

        ll.addHead(w);
        ll.addHead(wd);
        ll.addHead(wdd);
        ll.insertHere(3, k);

        LinkedList l2 = new LinkedList(ll);

        ll.removeHead();

        l2.addTail(a);
        l2.addTail(b);
        l2.addTail(c);

        System.out.println("list 1 " + ll + " \nlist 2 " + l2);

        /*LinkedList num = new LinkedList(1.3);
        num.addHead(11);
        num.addHead(-1.3);
        num.addHead(7);
        num.addHead(0.00001);*/

/*
        System.out.println("initial list ll: " + ll + " of size : "+ ll.size());

        ll.reverse();

        System.out.println("final list ll: " + ll + " of size : "+ ll.size());

//-----------------------------------------------------------------------------------------//

        System.out.println("initial list l2: " + l2 + " of size : "+ l2.size());

      //  System.out.println("ll merged with l2 : " + ll.merge(l2) + " of size : "+ ll.merge(l2).size());


        System.out.println("type of string aa : " + LinkedList.type(a) + " ---- " + a.getClass().getSimpleName());

        System.out.println("type of byte x : " + LinkedList.type(x) + " ---- " + ((Object)x).getClass().getSimpleName());
*/

       /* System.out.println("usorted list : " + num);


        num.sort();

        System.out.println("sorted list : " + num);

        System.out.println("7 is present int the list : " + num.contains(7.0));


        System.out.println("max of the list : " + ll.max());*/


    }
}
