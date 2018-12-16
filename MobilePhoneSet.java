public class MobilePhoneSet {
    Myset mymps=new Myset();

    /*Constructor*/
    public MobilePhoneSet()
    {	mymps=new Myset();	}

    public MobilePhoneSet(Myset a)
    {	mymps=a; }

    public boolean IsEmpty()
    {	return mymps.IsEmpty();		}

    public boolean IsMember (MobilePhone m)
    {	return mymps.IsMember(m);	}

    public void Insert(MobilePhone m)
    {	mymps.Insert(m);	}

	/*public void Insert(MobilePhoneSet mps)
	{
		Myset temp=mps.mymps;
		Node tempn = temp.my.head.next;
		while(tempn=!temp.my.tail)
		{
			this.Insert()
		}
	} */

    public void Delete(MobilePhone m)
    {	mymps.Delete(m);	}

    public MobilePhoneSet Union(MobilePhoneSet mps)
    {
        MobilePhoneSet hasunionmps= new MobilePhoneSet();
        hasunionmps = new MobilePhoneSet (this.mymps.Union(mps.mymps)) ;
        return hasunionmps;
    }
    public MobilePhoneSet Intersection(MobilePhoneSet mps)
    {
        MobilePhoneSet hasInt=new MobilePhoneSet();
        hasInt=new MobilePhoneSet( this.mymps.Intersection(mps.mymps) );
        return hasInt;
    }
    public MobilePhone retmobile(int a)
    {
        Node temp=mymps.my.head.next;
        MobilePhone t=(MobilePhone)temp.o;
        while(temp!=mymps.my.tail)
        {
            if(t.id==a)
            {	return t;	}
            temp=temp.next;
        }
        return new MobilePhone(a);//according to what switchonMobile a b wants
    }
    public MobilePhone retmobile2(int a) //throws Exception acc to whatever switchOffMobile demands
    {

        Node temp=mymps.my.head.next;
        MobilePhone t=null;
        while(temp!=mymps.my.tail)
        {t=(MobilePhone)temp.o;
            if(t.id==a)
            {	return t;	}
            temp=temp.next;
        }
        return null;
        //throw new Exception();
    }
    public String printids()
    {
        Node temp =mymps.my.head.next;
        String s=new String();
        MobilePhone mp=null;
        while(temp!=mymps.my.tail && mp.stat==true)
        { 
			mp=(MobilePhone)temp.o;
            s=s+" "+mp.id;
            temp=temp.next;
        }
        return s;
    }
}