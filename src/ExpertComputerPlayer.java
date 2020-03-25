import java.util.ArrayList;

public class ExpertComputerPlayer extends ComputerPlayer
{
    private static boolean hit = false;
    private static boolean reTry = false;
    private static Location hiit;
    private static int x;

    public ExpertComputerPlayer(String name)
    {
        super(name);
    }

    @Override
    public boolean attack(Player enemy, Location loc )
    {
        if(hit)
            return vertical(enemy);
        if(reTry)
            return horizontal();

        ArrayList<Location> positions = new ArrayList<>();

        for (int i = 0; i < getGuessBoard().length; i++)
            for (int j = 0; j < getGuessBoard().length; j++)
                if(j % 2 == 0 && i % 2 == 0)
                    positions.add(new Location(i, j));
                else if(j % 2 == 1 && i % 2 == 1)
                    positions.add(new Location(i,j));

        int random = 0;

        while(getGuessBoard()[positions.get(random).getRow()] [positions.get(random).getCol()] != 0)
        {
            random = (int) (Math.random() * 50);
        }

        int row = positions.get(random).getRow();
        int col = positions.get(random).getCol();

        Location locx = new Location(row, col);
        if(enemy.hasShipAtLocation(locx))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(locx).takeHit(locx);
            hit = true;
            Location hiit = locx;
            x = 1;

            if(enemy.getShip(locx).isSunk())             // determines if the ship sunk due to the recent attack
            {
                enemy.removeShip(enemy.getShip(locx));
                return true;
            }

        }
        else
            getGuessBoard()[row][col] = -1;

        return false;
    }

    private static boolean horizontal()
    {
        return false;
    }

    private boolean vertical(Player enemy)
    {
        Location loc = new Location(hiit.getRow(), hiit.getCol() + x);
        int row = loc.getRow();
        int col = loc.getCol();

        if(enemy.hasShipAtLocation(loc))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(loc).takeHit(loc);
            hit = true;
            Location hiit = loc;

            if(enemy.getShip(loc).isSunk())             // determines if the ship sunk due to the recent attack
            {
                hit = false;
                enemy.removeShip(enemy.getShip(loc));
                return true;
            }
        }
        else
        {
            getGuessBoard()[row][col] = -1;

            if(x == 1)
                x = -1;
            else
            {
                hit = false;
                //reTry = true;
            }
        }

        return false;
    }

    @Override
    public void populateShips()
    {
        super.populateShips();
    }
}
