package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class JrInstruction implements Instruction {

    private final Register rs;

    public JrInstruction(Register rs) {
        this.rs = rs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rs).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JrInstruction)) {
            return false;
        }
        final JrInstruction other = (JrInstruction) obj;
        return new EqualsBuilder().append(this.rs, other.rs).isEquals();
    }

    @Override
    public String toString() {
        return String.format("jr %s", rs);
    }
}
