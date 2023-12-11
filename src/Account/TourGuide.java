package Account;
import java.util.ArrayList;
import java.time.LocalDate;
public class TourGuide {
    private String name;
    private String id;
    private int phonenum;
    private int age;
    private String assssigntrip;
    private ArrayList<LocalDate> tripsmade;
    private static int avalguides;
    public TourGuide (String name,String id,int phonenum,int age)
    {
        this.name = name;
        this.id = id;
        this.phonenum = phonenum;
        this.age = age;
        avalguides++;
    }

    public int getAge() {
        return age;
    }

    public static int getAvalguides() {
        return avalguides;
    }

    public String getAssssigntrip() {
        return assssigntrip;
    }

    public String getName() {
        return name;
    }

    public void setAssssigntrip(String assssigntrip) {
        if(assssigntrip==null && this.assssigntrip != null)
        {
            avalguides++;
        }
        else if(assssigntrip!=null && this.assssigntrip == null)
        {
            avalguides--;
        }
        this.assssigntrip = assssigntrip;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<LocalDate> getTripsmade() {
        return tripsmade;
    }

    public int getPhonenum() {
        return phonenum;
    }

    public String getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void setAvalguides(int avalguides) {
        TourGuide.avalguides = avalguides;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }

    public void setTripsmade(ArrayList<LocalDate> tripsmade) {
        this.tripsmade = tripsmade;
    }
    public void finishtrip(LocalDate tripdate)
    {
        assssigntrip =null;
        tripsmade.add(tripdate);
        avalguides++;
    }
    public int calcsalery()
    {
        int counter = 0;
        int tripcost = 500;
        int datediff=0;
        for (LocalDate tripdate:tripsmade) {
            datediff=LocalDate.now().getMonth().compareTo(tripdate.getMonth());
            if(datediff==1 || datediff==-11)
            {counter++;}
        }
        return counter*tripcost;
    }
    public void display ()
    {
        System.out.println("Name: "+name);
        System.out.println("Id: "+id);
        System.out.println("Phone number: "+phonenum);
        System.out.println("Age: "+age);
        System.out.println("Assigned trip: "+assssigntrip==null ? "Available":assssigntrip);
        System.out.println("Trips made: "+tripsmade.size());
    }
}
