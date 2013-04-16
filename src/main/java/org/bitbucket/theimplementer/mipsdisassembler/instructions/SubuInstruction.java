package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SubuInstruction implements Instruction {

    private final Register rd;
    private final Register rs;
    private final Register rt;

    public SubuInstruction(Register rd, Register rs, Register rt) {
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
        if (!(obj instanceof SubuInstruction)) {
            return false;
        }
        final SubuInstruction other = (SubuInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rs, other.rs).append(this.rt, other.rt).isEquals();
    }

    @Override
    public String toString() {
        return String.format("subu %s, %s, %s", rd, rs, rt);
    }
}
