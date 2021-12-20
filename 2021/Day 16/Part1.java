import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        String hex = Util.readHexFromFile("input.txt");
        String packet = Util.getBinaryFromHex(hex);
        Packet decodedPacket = PacketDecoder.decodePacket(packet);
        System.out.println(decodedPacket);
        System.out.println("Answer: " + getVersionSum(decodedPacket));
    }

    private static int getVersionSum(Packet packet) {
        int sum = 0;

        List<Packet> subPackets = packet.getSubPackets();
        for(int i=0; i<subPackets.size(); i++) {
            sum += getVersionSum(subPackets.get(i));
        }

        sum += packet.getVersion();

        return sum;
    }
}