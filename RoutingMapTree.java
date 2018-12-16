public class RoutingMapTree {
    Exchange root=null;
    public RoutingMapTree()
    {
        Exchange first = new Exchange(0);
        root=first;
        Exchange.allexchanges.Insert(new ExchangeNode(root));
        /*

         * Exchange has 1 constructor with int id as parameter and
         * another wiht int id and parent p
         */
        root.parent = null;
    }

    /*constructor*/
    public void addChild(Exchange p, int id)
    {
        Exchange child = new Exchange(id, p);
    }

    public void switchOn(	MobilePhone a, Exchange b	)
    {
        try
        {
            if (!a.stat) {
                a.switchOn();

                //	b.addinResidentSet(a);
                Exchange temp = b;
                if (temp == null) {
                    throw new Exception();
                }
                //  System.out.println(b.id);
                // System.out.println("yaha aaaaaaaaaaaya");
                // System.out.println(temp.isRoot());
                while (!temp.isRoot()) {  // System.out.println("yaha aaya");
                    temp.addinResidentSet(a);
                    temp = temp.getParent();

                }
                temp.addinResidentSet(a);
            }


            a.base_station = b;

        }
        catch(Exception e)
        {
            System.out.println("there is no Exchange with the given identifier ");
        }
    }

    public void switchOff(MobilePhone a)
    {
        try{
            if (a.stat)
            {
                a.switchOff();
            }
            if(a==null)
            {
                throw new Exception();
            }

        }
        catch(Exception e){
            System.out.println("error");
    }
    }
	
	public Exchange findPhone(MobilePhone m)
	{
		return m.base_station;
	}
	
	public Exchange lowestRouter(Exchange a, Exchange b)
	{
		while(a!=b)
		{
			a=a.parent;
			b=b.parent;
		}
		return a;
	}
	
	public ExchangeList routeCall(MobilePhone a, MobilePhone b)
	{
		ExchangeList rc=new ExchangeList();
		/*routecall list*/
		Exchange temp = a.base_station;
		Exchange ref=lowestRouter(a.base_station,b.base_station);/*fn runs just once if i store it*/
		while(temp!=ref)
		{
			rc.Insert( new ExchangeNode(temp) );
			temp=temp.parent;
		}
		rc.Insert( new ExchangeNode(ref) );/*from a to common top*/
		
		ExchangeList t=new ExchangeList();/*insert this list in reverse in rc to get final list*/
		temp=b.base_station;
		while(temp!=ref)
		{
			t.Insert(new ExchangeNode(temp));
			temp=temp.parent;
		}
		Node tn=t.el.tail.prev;//ExchangeList is on top of a LinkedList thus Node 
		
		while(tn!=t.el.head)
		{
			Exchange n=(Exchange)tn.o;
			rc.Insert( new ExchangeNode(n) );//Insert (ExchangeNode) is the fn definition
			tn=tn.prev;
		}
		
		return rc;	
	}
		
		/*}
		catch(Exception e)
		{	
		if(a.stat==false)
			System.out.println("Error - Mobile phone with identifier "+a.id+" is currently switched off");
		else
			System.out.println("Error - Mobile phone with identifier "+b.id+" is currently switched off");
		}*/

	
	public void movePhone(MobilePhone a, Exchange b)
	{
		try
		{
		if(a==null)
		{	throw new Exception();	}
		if(b==null)
		{	throw new Exception();	}
			if(a.stat )
			{
			Exchange temp=a.base_station;
			//System.out.println(a.base_station.id);//checking
			//String nn=temp.printresidentset();
			//System.out.println(nn+"haha");
			Exchange ref= lowestRouter(temp,b);
			//System.out.println(ref.id);
			//System.out.println(a.base_station.id);
			while(temp!=ref)
			{
				//String s=temp.printresidentset();
				//System.out.println(s);
				//System.out.println(temp.id);
				temp.residentset.Delete(a);
				temp=temp.parent;
			}
			
			temp=b;
			
			while(temp!=ref)
			{ 
				temp.residentset.Insert(a);
				temp=temp.parent;
			}
			a.base_station=b;
			//System.out.println(a.base_station.id);
			}
		}
		catch(Exception e)
		{
			if(a==null) System.out.println("Error - No mobile phone with identifier "+a.id+" found in the network") ;
			if(b==null) System.out.println("Error- No Exchange with the identifier");
		}
	}
	
	
    public String performAction(String actionMessage) //throws Exception
    {

        String [] arr= actionMessage.split(" ");
       // System.out.println(arr[0]);

        if(arr[0].equals("addExchange") )
        {
            /*Initialise a new exchange*/
			
            int b=Integer.parseInt(arr[2]);
            Exchange temp=new Exchange(b);

            int a =Integer.parseInt(arr[1]);
            try {
                Exchange p = Exchange.allexchanges.findexchange(a);
                if (p == null) {
                    throw new Exception();
                }
                Node n = Exchange.allexchanges.el.head.next;
          /*  while(n!=Exchange.allexchanges.el.tail){
                Exchange h=(Exchange)n.o;
                System.out.println(h.id);
                n=n.next;
            }*/
                temp.parent = p;
                
                if (Exchange.allexchanges.IsMember(new ExchangeNode(p))) {
                    Exchange.allexchanges.Insert(new ExchangeNode(temp));
                    p.addChild(temp);
                    //System.out.println(actionMessage);//check
					return "";
                }
            }
            catch(Exception e){
                System.out.println("No exchange of given identifier found");
            }
        }

        else if(arr[0].equals("switchOnMobile"))

        {
            int aa=Integer.parseInt(arr[1]);
            int bb=Integer.parseInt(arr[2]);

            MobilePhone a= MobilePhone.allMobiles.retmobile(aa);
            Exchange b=Exchange.allexchanges.findexchange(bb);
            MobilePhone.allMobiles.Insert(a);
            switchOn(a,b);
			//System.out.println(actionMessage);//check
            return "";
        }

        else if(arr[0].equals("switchOffMobile"))
        {
            int aa=Integer.parseInt(arr[1]);
            MobilePhone a=MobilePhone.allMobiles.retmobile2(aa);
           //if(a==null)System.out.println(1000);
            switchOff(a);
			//System.out.println(actionMessage);//check
            return "";
        }
        /*""*/
       else if(arr[0].equals("queryNthChild"))
        {
            try{
            int aa=Integer.parseInt(arr[1]);
            Exchange a=Exchange.allexchanges.findexchange(aa);
            if(a==null){throw new Exception();}

            int bb=Integer.parseInt(arr[2]);
            Exchange b= a.cel.getExchange(bb);
            String s= actionMessage +": "+ b.id;
            System.out.println(s);
            return s;
        }
        catch(Exception e){
            System.out.println("Exchange not found");
        }
    }

       else if(arr[0].equals("queryMobilePhoneSet"))
        {
        try
		{
            int aa=Integer.parseInt(arr[1]);
            Exchange a=Exchange.allexchanges.findexchange(aa);
            if(a==null){throw new Exception();}
            //String s=actionMessage + a.printresidentset();
           // if (a.residentset==null) System.out.println("1000");
            //System.out.println(a.residentset.mymps.my.getSize());
            Node n=a.residentset.mymps.my.head.next;
            String s="";
            MobilePhone lda=null;
            while(n.next!=a.residentset.mymps.my.tail)
			{
                lda=(MobilePhone)n.o;
                if(lda.status()){

                s=s+Integer.toString(lda.id)+", ";}
                n=n.next;
            }
            lda=(MobilePhone)n.o;
             s=s+Integer.toString(lda.id);
            System.out.println( actionMessage+ ": " + s );
            return (actionMessage+": "+s);
        }
            catch(Exception e)
			{
				System.out.println("Exchange not found");
			}
        }
		else if  ( arr[0].equals("findPhone") )
		{
			try
			{
				String s=new String();
				int i1=Integer.parseInt(arr[1]);
				MobilePhone m=MobilePhone.allMobiles.retmobile2(i1) ;
				//m might be null
				if(m==null)
				{
					throw new Exception();
				}
				Exchange base=m.base_station;
				s="queryFindPhone "+arr[1]+": "+ base.id;
				System.out.println(s);//chevk
				return s;
			}
			catch(Exception e)
			{	return ("queryFindPhone "+arr[1]+": " + "Error - No mobile phone with identifier "+arr[1]+ " found in the network");	}
		}
		else if(arr[0].equals("lowestRouter"))
		{
			//System.out.println("yahan aaya.inside querylowestrouter");//vhevk
			int a=Integer.parseInt(arr[1]);
			int b=Integer.parseInt(arr[2]);
			
			Exchange e1=Exchange.allexchanges.findexchange(a);
			if(e1==null) System.out.println("fuck null hai ye to e1");
			Exchange e2=Exchange.allexchanges.findexchange(b);
			if(e1==null) System.out.println("fuck null hai ye to e2");
			Exchange lrouter=lowestRouter(e1,e2);
			String s=new String();
			s="queryLowestRouter " + arr[1] +" " +arr[2] +": "+lrouter.id;
			System.out.println(s);
			return s;
		}
		else if( arr[0].equals("findCallPath") )
		{
			int a=Integer.parseInt(arr[1]);
			int b=Integer.parseInt(arr[2]);
			
			MobilePhone m1=MobilePhone.allMobiles.retmobile2(a);
			MobilePhone m2=MobilePhone.allMobiles.retmobile2(b);
			try{
			if(!(m1.stat && m2.stat))
			{
				/*if(m1.stat==false)
					System.out.println("Error - Mobile phone with identifier "+m1.id+" is currently switched off");
				else
					System.out.println("queryFindCallPath "+arr[1]+" "+arr[2]+": "+"Error - Mobile phone with identifier "+m2.id+" is currently switched off");
					return "";*/
					throw new Exception();
			}
			
			//if(m1==m2) System.out.println("fuck ho gya bhai");
			
			ExchangeList ans=new ExchangeList();
			ans=routeCall(m1,m2);
			//if (ans.size()==0 )
			//{	System.out.println("ans is null");	}
			String s=new String();
			s="queryFindCallPath "+arr[1]+" "+arr[2]+": ";
			
			Node temp=ans.el.head.next; //if(temp==null)  System.out.println("temp is null");	 
			Exchange t=(Exchange)temp.o;	//if(t==null) System.out.println("t is null");
			s=s+t.id;
			temp=temp.next;
			while(temp!=ans.el.tail)
			{
				t=(Exchange)temp.o;
				s=s+", "+t.id;
				temp=temp.next;
			}
			System.out.println(s);
			return s;
			}
			catch(Exception e){
			    if(m1.stat==false)
					return ("queryFindCallPath" +arr[1]+" "+arr[2]+": "+"Error - Mobile phone with identifier "+m1.id+" is currently switched off");
				else
					return ("queryFindCallPath "+arr[1]+" "+arr[2]+": "+"Error - Mobile phone with identifier "+m2.id+" is currently switched off");
			}
		}
		else if( arr[0].equals("movePhone") )
		{
			//System.out.println("Inside move phone");
			int a=Integer.parseInt(arr[1]);
			int b=Integer.parseInt(arr[2]);
			
			MobilePhone m=MobilePhone.allMobiles.retmobile2(a);
			//if(m.id==a) System.out.println("correct phone");
			int temp=m.base_station.id;
			Exchange e=Exchange.allexchanges.findexchange(b);
			//if(e.id==b) System.out.println("correct exchange");
			movePhone(m,e);
			//System.out.println(m.id+ "shifted from " +temp+"to "+ e.id +" "+ m.base_station.id);
			//System.out.println(actionMessage);
			return"";
		}
		//""
        return "";
	}
}	