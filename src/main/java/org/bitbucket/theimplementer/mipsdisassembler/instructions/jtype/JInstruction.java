package org.bitbucket.theimplementer.mipsdisassembler.instructions.jtype;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

public class JInstruction implements Instruction {

    private final int target;

    public JInstruction(int target) {
        this.target = target;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(target).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JInstruction == false) {
            return false;
        }
        final JInstruction other = (JInstruction) obj;
        return new EqualsBuilder().append(this.target, other.target).isEquals();
    }

    @Override
    public String toString() {
        return String.format("j 0x%s", Integer.toHexString(target));
    }
}