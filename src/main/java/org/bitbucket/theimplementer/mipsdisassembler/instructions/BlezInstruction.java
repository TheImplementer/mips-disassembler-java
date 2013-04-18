package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class BlezInstruction implements Instruction {

    private final Register rs;
    private final short immediate;

    public BlezInstruction(Register rs, short immediate) {
        this.rs = rs;
        this.immediate = immediate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).append(immediate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BlezInstruction)) {
            return false;
        }
        final BlezInstruction other = (BlezInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("blez %s, 0x%02x", rs, immediate);
    }
}
