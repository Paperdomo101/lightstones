package paperdomo101.lightstones.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum LightstoneType implements StringIdentifiable {
    FLOOR("floor"),
    ROOF("roof");
 
    private final String name;
 
    private LightstoneType(String name) {
       this.name = name;
    }
 
    public String toString() {
       return this.name;
    }
 
    public String asString() {
       return this.name;
    }
}
 
