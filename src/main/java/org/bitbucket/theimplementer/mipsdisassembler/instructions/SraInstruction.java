package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SraInstruction implements Instruction {

    private final Register rd;
    private final Register rt;
    private final int shiftAmount;

    public SraInstruction(Register rd, Register rt, int shiftAmount) {
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
        if (!(obj instanceof SraInstruction)) {
            return false;
        }
        final SraInstruction other = (SraInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rt, other.rt).append(this.shiftAmount, other.shiftAmount).isEquals();
    }

    @Override
    public String toString() {
        return String.format("sra %s, %s, %s", rd, rt, shiftAmount);
    }
}
