public class ExpertComputerPlayer extends ComputerPlayer
{
    public ExpertComputerPlayer(String name)
    {
        super(name);
    }

    @Override
    public boolean attack(Player enemy, Location loc )
    {
        return super.attack(enemy, loc);
    }

    @Override
    public void populateShips()
    {
        super.populateShips();
    }
}
