package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SubInstruction implements Instruction {

    private final Register rd;
    private final Register rs;
    private final Register rt;

    public SubInstruction(Register rd, Register rs, Register rt) {
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).append(this.rs).append(this.rt).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SubInstruction)) {
            return false;
        }
        final SubInstruction other = (SubInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rs, other.rs).append(this.rt, other.rt).isEquals();
    }

    @Override
    public String toString() {
        return String.format("sub %s, %s, %s", rd, rs, rt);
    }
}
