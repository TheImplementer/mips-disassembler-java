package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class BneInstruction implements Instruction {

    private final Register rt;
    private final Register rs;
    private final short immediate;

    public BneInstruction(Register rt, Register rs, short immediate) {
        this.rt = rt;
        this.rs = rs;
        this.immediate = immediate;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).append(this.rt).append(immediate).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BneInstruction)) {
            return false;
        }
        final BneInstruction other = (BneInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.rt, other.rt).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("bne %s, %s, 0x%02x", rt, rs, immediate);
    }
}
