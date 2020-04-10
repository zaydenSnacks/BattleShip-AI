public class Location
{
    private int row;
    private int col;
    private boolean hit = false;

    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public Location(int row, int col, boolean hit)
    {
        this.row = row;
        this.col = col;
        this.hit = hit;
    }

    public boolean isHit()
    {
        return hit;
    }

    public void setHit(boolean hit)
    {
        this.hit = hit;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ((Location)(obj)).getRow() == row && ((Location)(obj)).getCol() == col;
    }
}
