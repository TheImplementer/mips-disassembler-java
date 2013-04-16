package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class DivuInstruction implements Instruction {

    private final Register rs;
    private final Register rt;

    public DivuInstruction(Register rs, Register rt) {
        this.rs = rs;
        this.rt = rt;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).append(this.rt).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DivuInstruction)) {
            return false;
        }
        final DivuInstruction other = (DivuInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).append(this.rt, other.rt).isEquals();
    }

    @Override
    public String toString() {
        return String.format("divu %s, %s", rs, rt);
    }
}
