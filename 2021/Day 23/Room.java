import java.util.*;

public class Room {
    private ArrayDeque<String> room;
    private int roomSize;
    private String roomOf;
    static Map<Integer,String> roomPosOf;

    static {
        roomPosOf = new HashMap<>();
        roomPosOf.put(0,"A");
        roomPosOf.put(1,"B");
        roomPosOf.put(2,"C");
        roomPosOf.put(3,"D");
    }

    Room(String room, int idx) {
        this.room = new ArrayDeque<>();
        for(int i=room.length()-1; i>=0; i--) {
            String letter = room.substring(i,i+1);
            if(!letter.equals(".")) {
                this.room.push(letter);
            }
        }
        roomSize = room.length();
        roomOf = roomPosOf.get(idx);
    }

    Room(Room other) {
        this.room = new ArrayDeque<>(other.room);
        this.roomSize = other.roomSize;
        this.roomOf = other.roomOf;
    }

    int getSize() {
        return roomSize;
    }

    int getOccupiedSize() {
        return room.size();
    }

    int getStepsRequiredToEnter() {
        return getSize() - getOccupiedSize();
    }

    void push(String letter) {
        room.push(letter);
    }

    String pop() {
        return room.pop();
    }

    String peek() {
        return room.peek();
    }

    boolean isClean() {
        Iterator<String> iter = room.iterator();
        while(iter.hasNext()) {
            if(!iter.next().equals(roomOf)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String empty = String.join("",Collections.nCopies(getSize()-getOccupiedSize(),"."));
        String occupants = String.join("",room);
        return empty + occupants;
    }

}