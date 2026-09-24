package week4;

public class CoralCastle {

    private static final int FIRST_CAVE_NUMBER = 101; // The number of the first cave.
    private static final int NUMBER_OF_CAPACITIES = 4; // The number of different capacities.
    private static final int SMALLEST_CAPACITY = 2; // The capacity of the smallest caves.

    private String name; // The name of the castle.
    private Cave[] caves; // All caves of the castle, ordered by cave number.

    public CoralCastle(String name, int numberOfCaves) {
        this.name = name;
        this.caves = new Cave[numberOfCaves];
        for (int i = 0; i < numberOfCaves; i++) {
            this.caves[i] = new Cave(101 + i, (i % 4) + 2);

        }
    }

    public Cave checkIn(String guestName, int guestSize) {
        Guest guest = new Guest(guestName, guestSize);
        for (int i = 0; i < this.caves.length; i++) {
            if (guest.checkIn(this.caves[i])) {
                return this.caves[i];
            }
        }
        return null;
    }

    public boolean checkOut(String guestName) {
        for (int i = 0; i < this.caves.length; i++) {
            Guest guest = this.caves[i].getGuest();
            if (guest != null && guest.getName().equals(guestName)) {
                return guest.checkOut();
            }
        }
        return false;
    }

    public Cave getCaveByGuestName(String guestName) {
        for (int i = 0; i < this.caves.length; i++) {
            Guest guest = this.caves[i].getGuest();
            if (guest != null && guest.getName().equals(guestName)) {
                return this.caves[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "Castle " + this.name;
        for (int i = 0; i < this.caves.length; i++) {
            result += "\n" + this.caves[i];
        }
        return result;
    }
}
