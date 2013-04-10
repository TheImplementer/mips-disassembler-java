package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SllvInstruction implements Instruction {

    private final Register rd;
    private final Register rt;
    private final Register rs;

    public SllvInstruction(Register rd, Register rt, Register rs) {
        this.rd = rd;
        this.rt = rt;
        this.rs = rs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.rd).append(this.rt).append(this.rs).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SllvInstruction)) {
            return false;
        }
        final SllvInstruction other = (SllvInstruction) obj;
        return new EqualsBuilder().append(this.rd, other.rd).append(this.rt, other.rt).append(this.rs, other.rs).isEquals();
    }

    @Override
    public String toString() {
        return String.format("sllv %s, %s, %s", rd, rt, rs);
    }
}
