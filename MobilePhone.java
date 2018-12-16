public class MobilePhone
{
    int id;
    boolean stat=false;
    Exchange base_station;
    static MobilePhoneSet allMobiles=new MobilePhoneSet();

    /*Constructor1*/
    public MobilePhone (int a)
    {	id =a;
        //allMobiles.Insert(this);
    }

    public int number()
    {	return id;	}

    public boolean status()
    {	return stat;	}

    public void switchOn()
    {	stat=true;	}

    public void switchOff()
    {	stat=false;	}

    public Exchange location() //throws Exception
    {
        try {
            if (this.stat) {
                return base_station;
            } else {
                throw new Exception();
            }

        }
        catch(Exception e){
            System.out.println("the Mobile Phone is Switched Off");
    }
    return null;
    }
}
