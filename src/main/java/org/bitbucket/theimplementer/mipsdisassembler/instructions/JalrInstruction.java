package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class JalrInstruction implements Instruction {

    private final Register rd;
    private final Register rs;

    public JalrInstruction(Register rd, Register rs) {
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).append(this.rs).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JalrInstruction)) {
            return false;
        }
        final JalrInstruction other = (JalrInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rs, other.rs).isEquals();
    }

    @Override
    public String toString() {
        return String.format("jalr %s, %s", rd, rs);
    }
}
