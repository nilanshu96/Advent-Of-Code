import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Util {
    static Pair<Player,Player> readPlayersFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            Player[] players = lines.stream().map(s -> s.split("Player \\d starting position:\\s+")[1])
                                .map(s -> new Player(Integer.parseInt(s)))
                                .toArray(Player[]::new);
            return new Pair<>(players[0], players[1]);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Pair<Integer,Integer> readPlayersPositionsFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            Integer[] positions = lines.stream().map(s -> s.split("Player \\d starting position:\\s+")[1])
                                .map(s -> Integer.decode(s))
                                .toArray(Integer[]::new);
            return new Pair<>(positions[0], positions[1]);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}