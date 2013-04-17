package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class LuiInstruction implements Instruction {

    private final Register rt;
    private final short immediate;

    public LuiInstruction(Register rt, short immediate) {
        this.rt = rt;
        this.immediate = immediate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rt).append(immediate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LuiInstruction)) {
            return false;
        }
        final LuiInstruction other = (LuiInstruction) obj;
        return new EqualsBuilder().append(this.rt, other.rt).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("lui %s, 0x%s", rt, immediate);
    }
}
