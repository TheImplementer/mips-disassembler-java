package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class LwlInstruction implements Instruction {

    private final Register rt;
    private final Register rs;
    private final short immediate;

    public LwlInstruction(Register rt, Register rs, short immediate) {
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
        if (!(obj instanceof LwlInstruction)) {
            return false;
        }
        final LwlInstruction other = (LwlInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.rt, other.rt).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("lwl %s, 0x%02x(%s)", rt, immediate, rs);
    }
}
