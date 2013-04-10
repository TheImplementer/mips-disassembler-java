package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class BreakInstruction implements Instruction {
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BreakInstruction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("break");
    }
}
