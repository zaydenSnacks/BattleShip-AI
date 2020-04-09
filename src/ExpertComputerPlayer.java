import java.util.ArrayList;

public class ExpertComputerPlayer extends ComputerPlayer
{
    ArrayList<Location> positions = new ArrayList<>();
    private boolean bool;
    private Location original;
    private int row;
    private int col;
    private int hitPlace = 0;

    public ExpertComputerPlayer(String name)
    {
        super(name);
    }

    @Override
    public boolean attack(Player enemy, Location loc )
    {
        positions.clear();
        checkerBoard();
        bool = fullCBoard();


        if(!original.isHit())           // figure this out (error from not initializing)
            randomPos();
        else
            vertical();



        row = positions.get(hitPlace).getRow();
        col = positions.get(hitPlace).getCol();

        Location locx = new Location(row, col);
        if(enemy.hasShipAtLocation(locx))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(locx).takeHit(locx);
            original = new Location(locx.getRow(), locx.getCol(), true);

            if(enemy.getShip(locx).isSunk())
            {
                enemy.removeShip(enemy.getShip(locx));
                return true;
            }

        }
        else
            getGuessBoard()[row][col] = -1;


        return false;
    }

    private void vertical()
    {

    }

    private void randomPos()
    {
        int x = 50;
        if(bool)
            x = 100;

        while(getGuessBoard()[positions.get(hitPlace).getRow()] [positions.get(hitPlace).getCol()] != 0)
        {
            hitPlace = (int) (Math.random() * x);
        }
    }

    private boolean fullCBoard()
    {
        for (int i = 0; i < positions.size(); i++)
        {
            if(getGuessBoard()[positions.get(i).getRow()] [positions.get(i).getCol()] == 0)
                break;
            else if(i == positions.size() - 1)
            {
                positions.clear();
                for (int j = 0; j < getGuessBoard().length; j++)
                {
                    for (int k = 0; k < getGuessBoard().length; k++)
                    {
                        positions.add(new Location(j, k));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void checkerBoard()
    {
        for (int i = 0; i < getGuessBoard().length; i++)
            for (int j = 0; j < getGuessBoard().length; j++)
                if(j % 2 == 0 && i % 2 == 0)
                    positions.add(new Location(i, j));
                else if(j % 2 == 1 && i % 2 == 1)
                    positions.add(new Location(i,j));
    }

    @Override
    public void populateShips()
    {
        super.populateShips();
    }
}
