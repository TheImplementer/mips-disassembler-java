package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class UnknownRegisterException extends RuntimeException {

    private final int register;

    public UnknownRegisterException(int register) {
        this.register = register;
    }

    public String toString() {
        return String.format("UnknownRegisterException[register=0x%s]", Integer.toHexString(register));
    }
}
