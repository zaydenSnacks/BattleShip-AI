import java.util.ArrayList;

public class ExpertComputerPlayer extends ComputerPlayer
{
    ArrayList<Location> positions = new ArrayList<>();
    private boolean bool;
    private Location original = new Location(-1, -1);
    private int row;
    private int col;
    private int hitPlace = 0;
    private Player enemy;
    private int add = 1;
    private int countH;
    private int countV;
    private boolean methdRun = false;



    public ExpertComputerPlayer(String name)
    {
        super(name);
    }

    @Override
    public boolean attack(Player enemy, Location loc )
    {
        this.enemy = enemy;         // watch this
        positions.clear();
        checkerBoard();
        bool = fullCBoard();


        if(original.isHit())
            return check();
        else
            randomPos();



        row = positions.get(hitPlace).getRow();
        col = positions.get(hitPlace).getCol();

        Location locx = new Location(row, col);
        if(enemy.hasShipAtLocation(locx))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(locx).takeHit(locx);
            original = new Location(locx.getRow(), locx.getCol(), true);
            countV = 0;
            countH = 0;
            methdRun = false;

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

    private boolean check()
    {
        if(!methdRun)
        {
            row = original.getRow();
            col = original.getCol();
        }
        System.out.println("Row and column: " + row + ", " + col);
        System.out.println("CountV: " + countH);
        if(countH < 2)
        {
            if (col == 0)
            {
                add = 1;
                return horizontal();
            }
            else if (col == 9)
            {
                add = -1;
                return horizontal();
            }
            else
                return horizontal();
        }
        else
        {
            if (row == 0)
            {
                add = 1;
                return vertical();
            }
            else if (row == 9)
            {
                add = -1;
                return vertical();
            }
            else
                return vertical();
        }

    }

    private boolean vertical()
    {
        if(countV == 1)
            add = add * -1;

        row = row + add;

        checkV();

        Location locx = new Location(row, col);
        if(enemy.hasShipAtLocation(locx))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(locx).takeHit(locx);
            methdRun = true;

            if(enemy.getShip(locx).isSunk())
            {
                original.setHit(false);
                enemy.removeShip(enemy.getShip(locx));
                return true;
            }

        }
        else
        {
            countV++;
            getGuessBoard()[row][col] = -1;
            methdRun = false;
        }


        return false;
    }

    private boolean horizontal()
    {
        if(countH == 1)
            add = add * -1;

        col = col + add;

        checkH();

        Location locx = new Location(row, col);
        if(enemy.hasShipAtLocation(locx))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(locx).takeHit(locx);
            methdRun = true;

            if(enemy.getShip(locx).isSunk())
            {
                original.setHit(false);
                enemy.removeShip(enemy.getShip(locx));
                return true;
            }

        }
        else
        {
            countH++;
            getGuessBoard()[row][col] = -1;
            methdRun = false;
        }


        return false;
    }

    private void checkH()
    {
        if(getGuessBoard()[row][col] == 1 || getGuessBoard()[row][col] == -1)
        {
                col = original.getCol();

                add = add * -1;
                col = col + add;

                if(getGuessBoard()[row][col] == -1 || getGuessBoard()[row][col] == 1)
                    vertical();

        }
    }

    private void checkV()
    {

        if(getGuessBoard()[row][col] == 1 || getGuessBoard()[row][col] == -1)
        {
            row = original.getCol();

            add = add * -1;
            row = row + add;

            if(getGuessBoard()[row][col] == -1 || getGuessBoard()[row][col] == 1)
                horizontal();

        }
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
