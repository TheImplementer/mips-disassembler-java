package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class UnknownInstructionException extends RuntimeException {

    private final int opcode;

    public UnknownInstructionException(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public String getMessage() {
        return String.format("UnknownInstructionException[opcode=0x%s]", Integer.toHexString(opcode));
    }
}
