package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SllInstruction implements Instruction {

    private final Register rd;
    private final Register rt;
    private final int shiftAmount;

    public SllInstruction(Register rd, Register rt, int shiftAmount) {
        this.rd = rd;
        this.rt = rt;
        this.shiftAmount = shiftAmount;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).append(this.rt).append(this.shiftAmount).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SllInstruction)) {
            return false;
        }
        final SllInstruction other = (SllInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rt, other.rt).append(this.shiftAmount, other.shiftAmount).isEquals();
    }

    @Override
    public String toString() {
        return String.format("sll %s, %s, %s", rd, rt, shiftAmount);
    }
}
