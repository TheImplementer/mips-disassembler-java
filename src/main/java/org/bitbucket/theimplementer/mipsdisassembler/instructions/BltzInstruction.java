package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class BltzInstruction implements Instruction {

    private final Register rs;
    private final short immediate;

    public BltzInstruction(Register rs, short immediate) {
        this.rs = rs;
        this.immediate = immediate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).append(immediate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BltzInstruction)) {
            return false;
        }
        final BltzInstruction other = (BltzInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("bltz %s, 0x%02x", rs, immediate);
    }
}
