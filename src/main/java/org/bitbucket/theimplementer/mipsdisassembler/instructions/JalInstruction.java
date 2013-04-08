package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class JalInstruction implements Instruction {

    private final int target;

    public JalInstruction(int target) {
        this.target = target;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(target).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JalInstruction)) {
            return false;
        }
        final JalInstruction other = (JalInstruction) obj;
        return new EqualsBuilder().append(this.target, other.target).isEquals();
    }

    @Override
    public String toString() {
        return String.format("jal 0x%s", Integer.toHexString(target));
    }
}
