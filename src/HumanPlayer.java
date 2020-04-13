public class HumanPlayer extends Player
{
    public HumanPlayer(String name)
    {
        super(name);
        populateShips();
    }

    /**
     * Attack the specified Location loc.  Marks
     *   the attacked Location on the guess board
     *   with a positive number if the enemy Player
     *   controls a ship at the Location attacked;
     *   otherwise, if the enemy Player does not
     *   control a ship at the attacked Location,
     *   guess board is marked with a negative number.
     *
     * If the enemy Player controls a ship at the attacked
     *   Location, the ship must add the Location to its
     *   hits taken.  Then, if the ship has been sunk, it
     *   is removed from the enemy Player's list of ships.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();

        if(enemy.hasShipAtLocation(loc))
        {
            getGuessBoard()[row][col] = 1;
            enemy.getShip(loc).takeHit(loc);

            if(enemy.getShip(loc).isSunk())
            {
                enemy.removeShip(enemy.getShip(loc));
                return true;
            }
        }
        else
            getGuessBoard()[row][col] = -1;

        return false;
    }


    /**
     * Construct an instance of
     *
     *   AircraftCarrier,
     *   Destroyer,
     *   Submarine,
     *   Cruiser, and
     *   PatrolBoat
     *
     * and add them to this Player's list of ships.
     */
    @Override
    public void populateShips()
    {
        //These are the locations for each of the ships for Computer Player
        Location[] aircraft = {new Location(3, 1),
                new Location(4, 1),
                new Location(5, 1),
                new Location(6, 1),
                new Location(7, 1)};
        addShip(new AircraftCarrier(aircraft));

        Location[] destroyer = {new Location(4, 4),
                new Location(4, 5),
                new Location(4, 6),
                new Location(4, 7)};
        addShip(new Destroyer(destroyer));

        Location[] cruiser = {new Location(6, 3),
                new Location(7, 3),
                new Location(8, 3)};
        addShip(new Cruiser(cruiser));

        Location[] submarine = {new Location(6, 6),
                new Location(6, 7),
                new Location(6, 8)};
        addShip(new Submarine(submarine));

        Location[] patrol = {new Location(0, 7),
                new Location(1, 7)};
        addShip(new PatrolBoat(patrol));
    }
}
