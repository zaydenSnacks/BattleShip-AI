import java.util.ArrayList;
import java.util.List;

public abstract class Ship
{
    private int length;
    private List<Location> locations;
    private List<Location> hitsTaken;

    public Ship(int length)
    {
        this.length = length;
        locations = new ArrayList<>();
        hitsTaken = new ArrayList<>();
    }

    public void addLocation(Location... loc)
    {

        for (int i = 0; i < loc.length; i++)    // loops through array and adds locations in array list
            locations.add(loc[i]);

    }

    public List<Location> getLocations()
    {
        return this.locations;
    }

    /**
     * Add Location loc to hitsTaken.
     *
     * @param loc
     */
    public void takeHit(Location loc)
    {
        hitsTaken.add(loc);
    }

    /**
     * Returns true if the number of hits taken is
     *   equal to the length of this ship.
     *
     * @return
     */
    public boolean isSunk()
    {
        return(hitsTaken.size() == length);
    }
}
