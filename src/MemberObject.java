public class MemberObject
{
    //Attributes
    private String     name;
    private String     lastName;
    private int        age;
    private int        id;
    private int        lastPayment;
    private boolean    memberStatus;
    private boolean    active;
    private boolean    deletedMember;

    //Methods
    public MemberObject(String name, String lastName, int age, int id, boolean memberStatus, boolean active, int lastPayment, boolean deletedMember)
    {
        this.name          = name;
        this.lastName      = lastName;
        this.age           = age;
        this.id            = id;
        this.memberStatus  = memberStatus;
        this.active        = active;
        this.lastPayment   = lastPayment;
        this.deletedMember = deletedMember;
    }

    public void setName(String name)
    {
        this.name          = name;
    }

    public void setLastName(String lastName)
    {
        this.lastName      = lastName;
    }

    public void setAge(int age)
    {
        this.age           = age;
    }

    public void setMemberStatus (boolean memberStatus)
    {
        this.memberStatus  = memberStatus;
    }

    public void setActive (boolean active)
    {
        this.active        = active;
    }

    public void setLastPayment (int lastPayment)
    {
        this.lastPayment   = lastPayment;
    }

    public void setDeletedMember (boolean deletedMember)
    {
        this.deletedMember  = deletedMember;
    }

    public String name()
    {
        return name;
    }

    public String lastName()
    {
        return lastName;
    }

    public int age()
    {
        return age;
    }

    public int id()
    {
        return id;
    }

    public boolean memberStatus()
    {
        return memberStatus;
    }

    public boolean active()
    {
        return active;
    }

    public int getPaymentYear()
    {
        return lastPayment;
    }

    public boolean getDeletedMember()
    {
        return deletedMember;
    }

}
