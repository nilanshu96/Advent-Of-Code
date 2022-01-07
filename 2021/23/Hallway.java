import java.util.*;

public class Hallway {
    String hallway;
    static Map<Character,Integer> roomPosOf;

    static {
        roomPosOf = new HashMap<>();
        roomPosOf.put('A',2);
        roomPosOf.put('B',4);
        roomPosOf.put('C',6);
        roomPosOf.put('D',8);
    }

    Hallway(String hallway) {
        this.hallway = hallway;
    }

    Hallway(Hallway other) {
        this.hallway = other.hallway;
    }

    @Override
    public String toString() {
        return hallway;
    }

    //Calculates possible moves where an amphipod can reach its designated room
    //Only calculates the steps needed for an amphipod to reach right
    //outside their designated room.
    List<Triplet<Hallway,Character,Integer>> getValidMovesFromCharToRoom() {
        List<Triplet<Hallway,Character,Integer>> validMoves = new ArrayList<>();
        for(int i=0; i<hallway.length(); i++) {
            if(hallway.substring(i,i+1).matches("[ABCD]")) {
                char amphipod = hallway.charAt(i);
                int amphipodPos = i;
                int roomPos = roomPosOf.get(amphipod);
                if(amphipodPos < roomPos && hallway.substring(amphipodPos+1,roomPos+1).matches("\\.+")) {
                    StringBuilder sb = new StringBuilder(hallway);
                    sb.setCharAt(i,'.');
                    validMoves.add(new Triplet<>(new Hallway(sb.toString()),amphipod,roomPos-amphipodPos));
                } else if(amphipodPos > roomPos && hallway.substring(roomPos, amphipodPos).matches("\\.+")) {
                    StringBuilder sb = new StringBuilder(hallway);
                    sb.setCharAt(i,'.');
                    validMoves.add(new Triplet<>(new Hallway(sb.toString()),amphipod,amphipodPos-roomPos));
                }
            }
        }
        return validMoves;
    }

    //Calculates the possible places an amphipod can go within the hallway 
    //from their position 'pos'
    List<Pair<Hallway,Integer>> getValidMovesFromPos(int pos, char amphipod) {
        int i = pos - 1;
        List<Pair<Hallway,Integer>> moves = new ArrayList<>();
        while(i >= 0 && hallway.charAt(i) == '.') {
            if(!isRoomPos(i)) {
                StringBuilder sb = new StringBuilder(hallway);
                sb.setCharAt(i, amphipod);
                moves.add(new Pair<>(new Hallway(sb.toString()),pos - i));
            }
            i--;
        }

        i = pos + 1;
        while(i < hallway.length() && hallway.charAt(i) == '.') {
            if(!isRoomPos(i)) {
                StringBuilder sb = new StringBuilder(hallway);
                sb.setCharAt(i, amphipod);
                moves.add(new Pair<>(new Hallway(sb.toString()),i - pos));
            }
            i++;
        }
        return moves;
    }

    private static boolean isRoomPos(int pos) {
        return pos >=2 && pos <=8 && pos%2==0;
    }
}