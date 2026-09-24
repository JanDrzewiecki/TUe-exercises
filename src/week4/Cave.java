package week4;

public class Cave {

    private int number; // The cave number, for example 101.
    private int capacity; // The largest guest size that fits in this cave.
    private Guest guest; // The guest staying in this cave, or null if the cave is free.

    public Cave(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public int getNumber() {
        return this.number;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public boolean isFree() {
        return this.guest == null;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        String state;
        if (isFree()) {
            state = "free";
        } else {
            state = "guest=" + this.guest;
        }
        return "Cave[" + this.number + ", capacity=" + this.capacity + ", " + state + "]";
    }
}
