package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class MthiInstruction implements Instruction {

    private final Register rs;

    public MthiInstruction(Register rs) {
        this.rs = rs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MthiInstruction)) {
            return false;
        }
        final MthiInstruction other = (MthiInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).isEquals();
    }

    @Override
    public String toString() {
        return String.format("mthi %s", rs);
    }
}
