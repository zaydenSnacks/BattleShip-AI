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
        Location[] array1 = {new Location(1, 1), new Location(2, 1),
                new Location(3, 1), new Location(4, 1),
                new Location(5, 1)};
        Location[] array2 = {new Location(1, 2), new Location(2, 2),
                new Location(3, 2), new Location(4, 2)};
        Location[] array3 = {new Location(1, 3), new Location(2, 3),
                new Location(4, 3)};
        Location[] array4 = {new Location(1, 4), new Location(2, 4),
                new Location(3, 4)};
        Location[] array5 = {new Location(1, 5),
                new Location(2, 5)};


        addShip(new AircraftCarrier(array1));           // populating the ship array
        addShip(new Destroyer(array2));
        addShip(new Cruiser(array3));
        addShip(new Submarine(array4));
        addShip(new PatrolBoat(array5));
    }
}
