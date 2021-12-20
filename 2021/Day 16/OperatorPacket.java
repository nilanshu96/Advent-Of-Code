import java.util.List;
import java.util.ArrayList;

public class OperatorPacket extends Packet {
    
    private List<Packet> subPackets = new ArrayList<>();

    OperatorPacket(int version, int typeId) {
        super(version, typeId);
    }

    public void addPacket(Packet packet) {
        subPackets.add(packet);
    }

    @Override
    public List<Packet> getSubPackets() {
        return new ArrayList<>(subPackets);
    }

    public int getSubPacketsSize() {
        return subPackets.size();
    }

    public void removePacket(Packet packet) {
        subPackets.remove(packet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{" + getVersion() + "," + getTypeId() + ", sub packets: [ ");

        for(int i=0; i<subPackets.size(); i++) {
            sb.append(subPackets.get(i).toString());
        }

        sb.append(" ]");

        return sb.toString();
    }
}