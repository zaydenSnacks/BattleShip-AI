public class Cruiser extends Ship
{
    /**
     * Construct a Cruiser with a length
     * of 3 and the specified Locations.
     *
     * @param locations
     */
    public Cruiser(Location... locations)
    {
        super(3);
        for(Location loc : locations)
            addLocation(loc);


    }
}