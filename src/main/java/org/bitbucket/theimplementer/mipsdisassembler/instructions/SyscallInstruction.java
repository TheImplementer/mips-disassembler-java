package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.equality.EqualsBuilder;
import net.emaze.dysfunctional.hashing.HashCodeBuilder;

public class SyscallInstruction implements Instruction {

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SyscallInstruction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("syscall");
    }
}
