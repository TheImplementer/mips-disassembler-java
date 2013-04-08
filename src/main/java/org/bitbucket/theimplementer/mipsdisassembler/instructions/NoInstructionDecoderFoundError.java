package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class NoInstructionDecoderFoundError extends RuntimeException {

    public NoInstructionDecoderFoundError(Integer instruction) {
        super(String.format("cannot find a decoder for specified instruction: %s", Integer.toHexString(instruction)));
    }
}
