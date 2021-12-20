public class PacketDecoder {

    private static int MIN_PACKET_SIZE = 11; //version(3) + typeId(3) + literal(5)

    static Packet decodePacket(String packet) {
        int idx = 0;
        int inputLength = packet.length();

        if(inputLength - idx < MIN_PACKET_SIZE) return null;

        if(isLiteralType(packet, idx)) {
            return decodeLiteralPacket(packet, idx).second;
        } else {
            return decodeOperatorPacket(packet, idx).second;
        }
    }

    private static Pair<Integer,? extends Packet> decodePacket(String packet, int idx) {
        int inputLength = packet.length();
        if(inputLength - idx < MIN_PACKET_SIZE) {
            return null;
        }

        if(isLiteralType(packet, idx)) {
            return decodeLiteralPacket(packet, idx);
        } else {
            return decodeOperatorPacket(packet, idx);
        }

    }

    private static Pair<Integer, LiteralPacket> decodeLiteralPacket(String packet, int idx) {
        int version = Util.binaryToDecimal(packet.substring(idx, idx+3));
        idx += 3;

        int typeId = Util.binaryToDecimal(packet.substring(idx, idx+3));
        idx += 3;

        LiteralPacket litPacket = new LiteralPacket(version, typeId);

        StringBuilder sb = new StringBuilder();
        while(packet.charAt(idx) == '1') {
            String literalBin = packet.substring(idx+1, idx+5);
            sb.append(literalBin);
            idx += 5;
        }

        String literalBin = packet.substring(idx+1, idx+5);
        sb.append(literalBin);
        idx += 5;

        litPacket.setValue(Util.binaryToLongDecimal(sb.toString()));

        return new Pair<>(Integer.valueOf(idx), litPacket);
    }

    private static Pair<Integer, OperatorPacket> decodeOperatorPacket(String packet, int idx) {
        int version = Util.binaryToDecimal(packet.substring(idx, idx+3));
        idx += 3;

        int typeId = Util.binaryToDecimal(packet.substring(idx, idx+3));
        idx += 3;

        OperatorPacket opPacket = new OperatorPacket(version, typeId);

        char lengthTypeId = packet.charAt(idx);
        idx++;

        if(lengthTypeId == '0') {
            int totalSubPacketBits = Util.binaryToDecimal(packet.substring(idx, idx+15));

            int subPacketBitCount = 0;
            idx += 15;

            while(subPacketBitCount < totalSubPacketBits) {
                Pair<Integer,? extends Packet> idxAndPacket = decodePacket(packet, idx);
                subPacketBitCount += idxAndPacket.first - idx;
                idx = idxAndPacket.first;
                opPacket.addPacket(idxAndPacket.second);
            }
        } else {
            int totalSubPackets = Util.binaryToDecimal(packet.substring(idx, idx+11));

            int subPacketCount = 0;
            idx += 11;

            while(subPacketCount < totalSubPackets) {
                Pair<Integer,? extends Packet> idxAndPacket = decodePacket(packet, idx);
                subPacketCount++;
                idx = idxAndPacket.first;
                opPacket.addPacket(idxAndPacket.second);
            }
        }

        return new Pair<>(Integer.valueOf(idx), opPacket);
    }

    private static boolean isLiteralType(String packet, int idx) {
        if(packet == null) return false;

        return "100".equals(packet.substring(idx + 3, idx + 6)); //100 is 4 in binary
    }
}