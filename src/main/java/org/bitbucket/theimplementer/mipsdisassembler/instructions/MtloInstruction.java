package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class MtloInstruction implements Instruction {

    private final Register rs;

    public MtloInstruction(Register rs) {
        this.rs = rs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MtloInstruction)) {
            return false;
        }
        final MtloInstruction other = (MtloInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).isEquals();
    }

    @Override
    public String toString() {
        return String.format("mtlo %s", rs);
    }
}
