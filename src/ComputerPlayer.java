public class ComputerPlayer extends Player
{
    public ComputerPlayer(String name)
    {
        super(name);
        populateShips();
    }

    /**
     * Randomly chooses a Location that has not been
     *   attacked (Location loc is ignored).  Marks
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
     * @param enemy The Player to attack.
     * @param locx The Location to attack.
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location locx)
    {
        int x = 0;
        int y = 0;

        while(getGuessBoard()[x][y] != 0)   // randomizes row and col until it finds a place on the board that has not been hit
        {
             x = (int) (Math.random() * 10);
             y = (int) (Math.random() * 10);
        }

        Location loc = new Location(x, y);          // creates new location object with randomized row and col

        if(enemy.hasShipAtLocation(loc))
        {
            getGuessBoard()[x][y] = 1;
            enemy.getShip(loc).takeHit(loc);

            if(enemy.getShip(loc).isSunk())             // determines if the ship sunk due to the recent attack
            {
                enemy.removeShip(enemy.getShip(loc));
                return true;
            }

        }
        else
            getGuessBoard()[x][y] = -1;

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
        Location[] array1 = {new Location(1, 1), new Location(2, 1),
                new Location(3, 1), new Location(4, 1),
                new Location(5, 1)};
        Location[] array2 = {new Location(1, 2), new Location(2, 2),
                new Location(3, 2), new Location(4, 2)};
        Location[] array3 = {new Location(1, 3), new Location(2, 3),
                new Location(3, 3)};
        Location[] array4 = {new Location(1, 4), new Location(2, 4),
                new Location(3, 4)};
        Location[] array5 = {new Location(1, 5),
                new Location(2, 5)};


        addShip(new AircraftCarrier(array1));
        addShip(new Destroyer(array2));
        addShip(new Cruiser(array3));
        addShip(new Submarine(array4));
        addShip(new PatrolBoat(array5));
    }
}
