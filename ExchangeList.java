public class ExchangeList {
    LinkedList el = new LinkedList();

    /* Constructors */
    public ExchangeList()
    {}

    public int size() {
        return el.getSize();
    }

    public boolean IsEmpty() {
        return el.isEmpty();
    }

    public boolean IsMember(ExchangeNode en) {
        return el.isMember(en.en);
    }

    public void Insert(ExchangeNode ee)
    {
        el.add(ee.en); /*add last hai !*/

    }

    public void Delete(ExchangeNode e1) {
        el.remove(e1.en);
    }

    public Exchange getExchange(int i) {
        Node temp = el.getNode(i);
        return (Exchange)temp.o;
    }
    public Exchange findexchange(int a)/* throws Exception */
    {
        Node temp=new Node();
		temp=Exchange.allexchanges.el.head.next;

        while(temp!=Exchange.allexchanges.el.tail)
        {Exchange temp1=(Exchange)temp.o ;
            if(temp1.id==a)
            { return temp1; }
            temp=temp.next;
        }
		
        if (temp==null)
        {  return null;}
        return null;//trivial
    }

	/*
	  whether or not an implementation for union and intersection is needed is
	  yet to be seen. depends upon wheter the assignment ppl consider the 2
	  operations as basic operations of ll or set
	 */
}

class ExchangeNode
{
    Node en = new Node();
    /*Constructor*/
    public ExchangeNode(Exchange e1) {
        en.o = e1;
    }


    public void SetNext(ExchangeNode n) {
        en.next = n.en;
    }

    public void SetPrevious(ExchangeNode p) {
        en.prev = p.en;
    }

    public void setExchange(Exchange e1) {
        en.o = e1;
    }
}



/* use setNext or en.en.next */

/*
 * Exchange e; ExchangeNode next; ExchangeNode prev; COnstructors public
 * ExchangeNode() { e=null; next=null; prev=null; }
 *
 * public ExchangeNode(Exchange e) { }
 *
 * public ExchangeNode(Exchange ee, ExchangeNode p, ExchangeNode n) { e=ee;
 * next=n; prev=p; }
 *
 *
 * public void setNext(ExchangeNode n) { next= n; }
 *
 * public void setPrev(ExchangeNode p) { prev=p; }
 *
 * public void setData(Exchange ee) { this.e =ee; }
 */
