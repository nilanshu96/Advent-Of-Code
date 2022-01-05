import java.util.*;

public class Room {
    private ArrayDeque<Character> room;
    private int roomSize;
    private char roomOf;
    static Map<Integer,Character> roomPosOf;

    static {
        roomPosOf = new HashMap<>();
        roomPosOf.put(0,'A');
        roomPosOf.put(1,'B');
        roomPosOf.put(2,'C');
        roomPosOf.put(3,'D');
    }

    Room(String room, int idx) {
        this.room = new ArrayDeque<>();
        for(int i=room.length()-1; i>=0; i--) {
            Character letter = room.charAt(i);
            if(!letter.equals('.')) {
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

    int getStepsRequiredToExit() {
        return getSize() - getOccupiedSize() + 1; // The 1 extra space is the space right outside the room
    }

    void push(Character letter) {
        room.push(letter);
    }

    Character pop() {
        return room.pop();
    }

    Character peek() {
        return room.peek();
    }

    boolean isClean() {
        Iterator<Character> iter = room.iterator();
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
        StringBuilder occupants = new StringBuilder();
        Iterator<Character> it = room.iterator();
        while(it.hasNext()) {
            occupants.append(it.next());
        }
        return empty + occupants.toString();
    }

}