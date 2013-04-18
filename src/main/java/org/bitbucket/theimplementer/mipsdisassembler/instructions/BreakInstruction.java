package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class BreakInstruction implements Instruction {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BreakInstruction;
    }

    @Override
    public String toString() {
        return String.format("break");
    }
}
