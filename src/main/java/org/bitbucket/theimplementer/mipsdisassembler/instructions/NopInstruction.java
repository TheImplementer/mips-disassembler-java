package org.bitbucket.theimplementer.mipsdisassembler.instructions;


public class NopInstruction implements Instruction {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NopInstruction;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
