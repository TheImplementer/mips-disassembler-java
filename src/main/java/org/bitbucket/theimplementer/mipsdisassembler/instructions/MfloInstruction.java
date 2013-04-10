package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class MfloInstruction implements Instruction {

    private final Register rd;

    public MfloInstruction(Register rd) {
        this.rd = rd;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MfloInstruction)) {
            return false;
        }
        final MfloInstruction other = (MfloInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).isEquals();
    }

    @Override
    public String toString() {
        return String.format("mflo %s", rd);
    }
}
