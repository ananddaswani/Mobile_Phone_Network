public class Myset {

    LinkedList my=new LinkedList();

    /*Constructor*/
    public Myset()
    {	 my=new LinkedList();	}
    public Myset(LinkedList a)
    {	my = a;	}

    public boolean IsEmpty()
    {	return (my.size==0);	 }

    public boolean IsMember(Object o)
    {
        Node temp=new Node(o,null,null);
        return my.isMember(temp);
    }

    public void Insert(Object o)
    {
        if( my.isMember(new Node(o))==true)
            return;
        else
        {
            Node temp=new Node(o,null,null);
            my.add(temp);
        }
    }
    public void Delete(Object o) {
        Node temp = new Node(o, null, null);
        try {
            if (my.isMember(temp))
                my.remove(temp);
            else
                throw new Exception();

        }
        catch(Exception e)
        {
           
        }
    }

    public Myset Union(Myset a)
    {
        LinkedList hasunion=a.my;
        Node temp=this.my.head.next;
        while(temp!=this.my.tail)
        {
            if( this.my.isMember((temp) ) )
                temp=temp.getNext();
            else
            {
                hasunion.add(temp);
                temp=temp.getNext();
            }
        }
        return new Myset(hasunion);
    }

    public Myset Intersection(Myset a)
    {
        LinkedList hasIntersection=new LinkedList();
        LinkedList l1 = a.my;
        LinkedList l2= this.my;
        Node temp=l1.head.next;

        while(temp!=l1.tail)
        {
            if( l2.isMember(temp) )
            {
                hasIntersection.add(temp);
            }
            temp=temp.next;
        }
        return new Myset(hasIntersection);
    }
}

class Node/*Setters,getters and constructors*/
{
    Object o;
    Node next;
    Node prev;

    /*Constructor1*/
    public Node()
    {
        prev=null;
        next = null;
        o = null;
    }
    /*Constructor2*/
    public Node(Object o,Node p,Node n)/*object,prev,next,*/
    {
        this.o = o;
        next = n;
        prev=p;
    }
    public Node(Object o1)
    {
        o=o1;
        prev=null;
        next = null;
    }
    /*ADD CONSTRUCTORS AS NEEDED!!!!!!!!!!!!!!!!!!!!!!!!!*/

    /*SETTERS*/
    public void setNext(Node n)
    {	next = n;	}

    public void setPrev(Node p)
    {	prev=p;		}

    public void setData(Object o)
    {	this.o = o;		}

    /*GETTERS- redundant as no longer protected*/
    public Node getNext()
    {	return next;	}
    public Node getPrev()
    {	return prev;	}
    public Object getData()
    {	return o;	}
}

/*Doubly linked list*/	/*size,isMember,getPrevious,getNext,add(add last),remove*/
class LinkedList
{
    Node head,tail;
    int size;
    /*Constructor*/
    public LinkedList()
    {
        size=0;
        head=new Node(null,null,null);
        tail=new Node(null,head,null);
        head.setNext(tail);
    }

    public int getSize()
    {	return size;	}

    public boolean isEmpty()
    {return(size==0);}

    public boolean isMember(Node a)
    {
        Node temp=head.getNext();  /*temp is a reference*/
        while( temp != tail )
        {
            if(temp.getData()==a.getData())
                return true;
            temp=temp.getNext();
        }
        if(temp == tail)
            return false;
        return true; /*just to avoid dtupid error*/
    }
    /*getters*/
    public Node getPrevious(Node a) //throws IllegalArgumentException
    {
        /*if(a==head)
            throw new IllegalArgumentException();
        else*/

        {
            return a.prev;
        }
    }

    public Node getNext(Node a )
    {	return a.getNext();	}

    public void add(Node a)     /*add last*/
    {
		/*Node temp=a;
		Node s=head.getNext();
		temp.setNext(s);
		temp.setPrev(head);
		head.setNext(temp);
		s.setPrev(temp);
		size++;
		add first was removed because add last is needed
		*/

        Node temp=a;
        Node l=tail.prev;
        temp.prev=l;
        temp.next=tail;
        tail.prev=temp;
        l.next=temp;
        size++;
    }

    public void remove(Node a)
    {
        Node p=a.getPrev();
        Node n=a.getNext();
        p.setNext(n);
        n.setPrev(p);
        a.setNext(null);
        a.setPrev(null);
        size--;
    }
    public Node getNode(int i)
    {
        Node temp=head.next;
        for(int j=1;j<=i;j++)
        {
            temp=temp.next;
        }
        return temp;
    }
}
