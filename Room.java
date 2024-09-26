public class Room {

    private int roomNumber;
    private String roomDescription;

    private Room n;
    private Room s;
    private Room w;
    private Room e;

    public Room(int roomNumber, String roomDescription) {
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getRoomDescription() {
        return roomDescription;
    }

    public Room getN() {
        return n;
    }
    public void setN(Room n) {
        this.n = n;
    }

    public Room getS() {
        return s;
    }
    public void setS(Room s) {
        this.s = s;
    }

    public Room getW() {
        return w;
    }
    public void setW(Room w) {
        this.w = w;
    }

    public Room getE() {
        return e;
    }
    public void setE(Room e) {
        this.e = e;

    }

    @Override
    public String toString() {

        return "Your character is in room " + roomNumber+". " + roomDescription;


    }

    }








