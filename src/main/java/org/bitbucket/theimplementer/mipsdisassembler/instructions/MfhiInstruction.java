package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class MfhiInstruction implements Instruction {

    private final Register rd;

    public MfhiInstruction(Register rd) {
        this.rd = rd;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MfhiInstruction)) {
            return false;
        }
        final MfhiInstruction other = (MfhiInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).isEquals();
    }

    @Override
    public String toString() {
        return String.format("mfhi %s", rd);
    }
}
