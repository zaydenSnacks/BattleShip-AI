public class Destroyer extends Ship
{
    /**
     * Construct a Destroyer with a length
     * of 4 and the specified Locations.
     *
     * @param locations
     */
    public Destroyer(Location... locations)
    {
        super(4);
        for(Location loc : locations)  // loops through locations array given to add each one to location of ship
            addLocation(loc);
    }
}