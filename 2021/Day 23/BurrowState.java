import java.util.stream.Collectors;
import java.util.*;

public class BurrowState implements Comparable<BurrowState> {
    private String state;
    private int roomSize;
    private Hallway hallway;
    private int cost;
    private Room[] rooms = new Room[4];
    static Map<Integer,Integer> posOfRoomInHallway;
    static Map<Character,Integer> roomIdxOf;
    static Map<Character,Integer> costOfAmphipods;

    static {
        posOfRoomInHallway = new HashMap<>();
        posOfRoomInHallway.put(0,2);
        posOfRoomInHallway.put(1,4);
        posOfRoomInHallway.put(2,6);
        posOfRoomInHallway.put(3,8);

        roomIdxOf = new HashMap<>();
        roomIdxOf.put('A',0);
        roomIdxOf.put('B',1);
        roomIdxOf.put('C',2);
        roomIdxOf.put('D',3);

        costOfAmphipods = new HashMap<>();
        costOfAmphipods.put('A',1);
        costOfAmphipods.put('B',10);
        costOfAmphipods.put('C',100);
        costOfAmphipods.put('D',1000);
    }

    BurrowState(List<String> state) {
        roomSize = state.size() - 3;
        this.state = state.stream().collect(Collectors.joining("\n"));
        hallway = new Hallway(state.get(1).substring(1,12));
        for(int i=0; i<4; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=2; j<state.size()-1; j++) {
                sb.append(state.get(j).charAt(2*(i+1)+1));
            }
            rooms[i] = new Room(sb.toString(), i);
        }
        cost = 0;
    }

    int getRoomSize() {
        return roomSize;
    }

    BurrowState(BurrowState other) {
        this(Arrays.asList(other.state.split("\n")));
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    Hallway getHallway() {
        return new Hallway(hallway);
    }

    Room[] getRooms() {
        Room[] roomsCopy = new Room[4];
        for(int i=0; i<4; i++) {
            roomsCopy[i] = new Room(rooms[i]);
        }
        return roomsCopy;
    }

    List<BurrowState> getNextValidBurrowStates() {
        List<BurrowState> states = new ArrayList<>();

        for(Triplet<Hallway,Character,Integer> h: hallway.getValidMovesFromCharToRoom()) {
            int roomIdx = roomIdxOf.get(h.second);
            if(rooms[roomIdx].isClean()){
                int newCost = (h.third + rooms[roomIdx].getStepsRequiredToEnter())*costOfAmphipods.get(h.second);
                Room[] newRooms = getRooms();
                newRooms[roomIdx].push(String.valueOf(h.second));
                BurrowState newState = Util.encodeHallwayRooms(h.first,newRooms);
                newState.setCost(this.cost + newCost);
                states.add(newState);
            }
        }

        if(!states.isEmpty()) {
            return states; //No need to return other possibilites as moving of an amphipod to room always takes priority
        }

        for(int i=0; i<4; i++) {
            if(!rooms[i].isClean()) {
                for(Pair<Hallway,Integer> h: hallway.getValidMovesFromPos(posOfRoomInHallway.get(i),rooms[i].peek().charAt(0))) {
                int newCost = (h.second + rooms[i].getStepsRequiredToEnter()) * costOfAmphipods.get(rooms[i].peek().charAt(0));
                Room[] newRooms = getRooms();
                newRooms[i].pop();
                BurrowState newState = Util.encodeHallwayRooms(h.first, newRooms);
                newState.setCost(this.cost + newCost);
                states.add(newState);
                }
            }
        }

        return states;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(this.getClass() != o.getClass()) {
            return false;
        }

        BurrowState other = (BurrowState)o;
        return this.state.equals(other.state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public int compareTo(BurrowState other) {
        return this.getCost() - other.getCost();
    }

}