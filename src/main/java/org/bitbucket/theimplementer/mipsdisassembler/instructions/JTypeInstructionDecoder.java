package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class JTypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_TYPE_BITMASK = 0xfc000000;
    public static final int JTYPE_INSTRUCTION_SIGNATURE = 0x04000000;

    @Override
    public Instruction perform(Integer instruction) {
        dbc.precondition(instruction != null, "instruction cannot be null");
        final int target = instruction.intValue() & (~INSTRUCTION_TYPE_BITMASK);
        return new JInstruction(target);
    }

    @Override
    public boolean accept(Integer element) {
        dbc.precondition(element != null, "element cannot be null");
        return (element.intValue() & INSTRUCTION_TYPE_BITMASK) == JTYPE_INSTRUCTION_SIGNATURE;
    }
}
