public class SwimResult
{
    private double time;
    private String disciplin;
    private String date;
    private String name;
    private String lastName;
    private String eventName;
    private int memberId;
    private boolean juniorOrSenior;
    private boolean isEvent; // true = Stævne

    public SwimResult(double time, String disciplin, String date, String name, String lastName, int memberId, boolean juniorOrSenior, String eventName, boolean isEvent)
    {
        this.time = time;
        this.disciplin = disciplin;
        this.date = date;
        this.name = name;
        this.memberId = memberId;
        this.juniorOrSenior = juniorOrSenior;
        this.lastName = lastName;
        this.eventName = eventName;
        this.isEvent = isEvent;
    }

    public double getTime()
    {
        return time;
    }

    public String getDisciplin()
    {
        return disciplin;
    }

    public String getDate()
    {
        return date;
    }

    public String getName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEventName()
    {
        return eventName;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public boolean getJuniorOrSenior()
    {
        return juniorOrSenior;
    }

    public boolean getIsEvent()
    {
        return isEvent;
    }

}
