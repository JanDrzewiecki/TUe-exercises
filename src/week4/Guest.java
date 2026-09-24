package week4;

public class Guest {

    private String name; // The name of the guest.
    private int size; // The size of the guest; a cave needs at least this capacity.
    private Cave cave; // The cave the guest is staying in, or null if the guest has no cave.

    public Guest(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public boolean checkIn(Cave newCave) {
        if (this.cave == null && newCave.isFree() && newCave.getCapacity() >= this.size) {
            this.cave = newCave;
            newCave.setGuest(this);
            return true;
        }
        return false;
    }

    public boolean checkOut() {
        if (this.cave != null) {
            this.cave.setGuest(null);
            this.cave = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Guest[name=" + this.name + ", size=" + this.size + "]";
    }
}
