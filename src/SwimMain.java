public class SwimMain
{
    public static void main(String[] args)
    {
        Members members = Members.getInstance();
        members.loadMembersToArrayList();
        SystemMenu.systemMenu();
    }
}