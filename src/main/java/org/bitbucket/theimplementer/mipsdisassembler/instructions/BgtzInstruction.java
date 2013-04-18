package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class BgtzInstruction implements Instruction {

    private final Register rs;
    private final short immediate;

    public BgtzInstruction(Register rs, short immediate) {
        this.rs = rs;
        this.immediate = immediate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).append(immediate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BgtzInstruction)) {
            return false;
        }
        final BgtzInstruction other = (BgtzInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("bgtz %s, 0x%02x", rs, immediate);
    }
}
