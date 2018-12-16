public class Exchange {
    int id;
    Exchange parent;
    MobilePhoneSet residentset=new MobilePhoneSet();
    ExchangeList cel=new ExchangeList();
    static ExchangeList allexchanges=new ExchangeList();
    //public static ExchangeSet es ;

    /* Constructor1 */
    public Exchange(int a) {
        id = a;
       // allexchanges.Insert(new ExchangeNode(this)); //problem here
    }

    /* Constructor 2 */
    public Exchange(int a, Exchange p) {
        id = a;
        parent = p;
        allexchanges.Insert(new ExchangeNode(this));
    }

    public int numChildren() {
        return cel.size();
    }

    public boolean isRoot()
    {
        if (parent == null)
            return true;
        else
            return false;
    }

    public Exchange child(int i) {
        Exchange temp = cel.getExchange(i);
        return temp;
    }

    public Exchange getParent() {
        return this.parent;
    }

    public MobilePhoneSet residentSet() {
        return residentset;
    }

    public void addinResidentSet(MobilePhone mp) {
        residentset.Insert(mp);
    }
    public void addChild(Exchange b)
    {
        this.cel.Insert(new ExchangeNode(b));
    }
    public String printresidentset()
    {
        String s=residentset.printids();
        return s;
    }


    /*
     * public void addinResidentSet( MobilePhoneSet mps) { residentset.a}
     */

    //public RoutingMapTree subTree() {}

    /*
     * public void setResidentSet(MobilePhoneSet mps)
     *
     * { residentset=mps; }
     *
     */
}
/*
class ExchangeSet
{
	/*a set of all exchanges formed so far

	Myset es=new Myset();
	/*exchangeset on top of myset

	public boolean isMember(Exchange b)
	{	return es.IsMember(b);	}

	public void insert(Exchange b)
	{	es.Insert(b);	}

	public Exchange findexchange(int a)
	{}
}*/