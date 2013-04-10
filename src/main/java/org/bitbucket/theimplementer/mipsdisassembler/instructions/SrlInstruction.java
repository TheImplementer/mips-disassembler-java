package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SrlInstruction implements Instruction {

    private final Register rd;
    private final Register rt;
    private final int shiftAmount;

    public SrlInstruction(Register rd, Register rt, int shiftAmount) {
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
        if (!(obj instanceof SrlInstruction)) {
            return false;
        }
        final SrlInstruction other = (SrlInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rt, other.rt).append(this.shiftAmount, other.shiftAmount).isEquals();
    }

    @Override
    public String toString() {
        return String.format("srl %s, %s, %s", rd, rt, shiftAmount);
    }
}
