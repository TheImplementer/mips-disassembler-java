package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class AddiuInstruction implements Instruction {

    private final Register rt;
    private final Register rs;
    private final short immediate;

    public AddiuInstruction(Register rt, Register rs, short immediate) {
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
        if (!(obj instanceof AddiuInstruction)) {
            return false;
        }
        final AddiuInstruction other = (AddiuInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.rt, other.rt).append(this.immediate, other.immediate).isEquals();
    }

    @Override
    public String toString() {
        return String.format("addiu %s, %s, 0x%s", rt, rs, immediate);
    }
}
