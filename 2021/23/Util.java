import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Util {
    static BurrowState readBurrowState(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            return new BurrowState(lines);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static BurrowState readBurrowState(String filename, String extraAmphipodsFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            List<String> extraAmphipods = Files.readAllLines(Paths.get(extraAmphipodsFile));
            lines.addAll(lines.size()-2,extraAmphipods);
            return new BurrowState(lines);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static BurrowState encodeHallwayRooms(Hallway hallway, Room[] rooms) {
        List<String> state = new ArrayList<>();
        state.add(String.join("",Collections.nCopies(13,"#")));
        String hall = "#" + hallway.toString() + "#";
        state.add(hall);
        String room1 = rooms[0].toString();
        String room2 = rooms[1].toString();
        String room3 = rooms[2].toString();
        String room4 = rooms[3].toString();
        String roomStart = "###" + room1.charAt(0) + "#" + room2.charAt(0) + "#" + room3.charAt(0) + "#" + room4.charAt(0) + "###";
        state.add(roomStart);
        for(int i=1; i<room1.length(); i++) {
            state.add("  #" + room1.charAt(i) + "#" + room2.charAt(i) + "#" + room3.charAt(i) + "#" + room4.charAt(i) + "#");
        }
        state.add("  #########");
        return new BurrowState(state);
    }

    static BurrowState getFinalBurrowState(BurrowState burrowState) {
        int roomSize = burrowState.getRoomSize();
        List<String> state = new ArrayList<>();
        state.add(String.join("",Collections.nCopies(13,"#")));
        state.add("#" + String.join("",Collections.nCopies(11,".")) + "#");
        String roomStart = "###A#B#C#D###";
        state.add(roomStart);
        for(int i=1; i<roomSize; i++) {
            state.add("  #A#B#C#D#");
        }
        state.add("  #########");
        return new BurrowState(state);
    }

    static void printBurrowStates(List<BurrowState> states) {
        if(states == null) return;

        for(BurrowState state: states) {
            System.out.println(state);
            System.out.println();
        }
    }
}