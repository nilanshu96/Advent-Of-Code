import java.util.List;
import java.util.Collections;

public class LiteralPacket extends Packet {

    private long value;

    LiteralPacket(int version, int typeId) {
        super(version, typeId);
    }

    @Override
    public List<Packet> getSubPackets() {
        return Collections.emptyList();
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{"+ getVersion() + "," + getTypeId() + "," + value + "}";
    }
}