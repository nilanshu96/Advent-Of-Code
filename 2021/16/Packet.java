import java.util.List;

abstract class Packet {
    private int version;
    private int typeId;

    Packet(int version, int typeId) {
        this.version = version;
        this.typeId = typeId;
    }

    int getVersion() {
        return version;
    }

    int getTypeId() {
        return typeId;
    }

    boolean isLiteral() {
        return typeId == 4;
    }

    abstract List<Packet> getSubPackets();

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj.getClass() != this.getClass()) {
            return false;
        }

        final Packet other = (Packet)obj;

        if( (other.version == this.version) && (other.typeId == this.typeId) ) {
            return true;
        }

        return false;
    }
}