import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        String hex = Util.readHexFromFile("input.txt");
        String packet = Util.getBinaryFromHex(hex);
        Packet decodedPacket = PacketDecoder.decodePacket(packet);
        System.out.println(getPacketExpressionValue(decodedPacket));
    }

    private static long getPacketExpressionValue(Packet packet) {
        int typeId = packet.getTypeId();
        List<Packet> subPackets = packet.getSubPackets();

        switch(typeId) {
            case 0:
                long sum = 0;
                for(int i=0; i<subPackets.size(); i++) {
                    sum += getPacketExpressionValue(subPackets.get(i));
                }
                return sum;
            case 1:
                long product = 1;
                for(int i=0; i<subPackets.size(); i++) {
                    product *= getPacketExpressionValue(subPackets.get(i));
                }
                return product;
            case 2:
                long min = Long.MAX_VALUE;
                for(int i=0; i<subPackets.size(); i++) {
                    long val = getPacketExpressionValue(subPackets.get(i));
                    min = val < min ? val: min;
                }
                return min;
            case 3:
                long max = Long.MIN_VALUE;
                for(int i=0; i<subPackets.size(); i++) {
                    long val = getPacketExpressionValue(subPackets.get(i));
                    max = val > max ? val: max;
                }
                return max;
            case 4:
                return ((LiteralPacket)packet).getValue();
            case 5:
                return getPacketExpressionValue(subPackets.get(0)) > getPacketExpressionValue(subPackets.get(1)) ? 1l : 0l;
            case 6:
                return getPacketExpressionValue(subPackets.get(0)) < getPacketExpressionValue(subPackets.get(1)) ? 1l : 0l;
            case 7:
                return getPacketExpressionValue(subPackets.get(0)) == getPacketExpressionValue(subPackets.get(1)) ? 1l : 0l;
        }

        return 0l;
    }
}