package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class JTypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_OPCODE_BITMASK = 0xfc000000;
    public static final int J_INSTRUCTION_OPCODE = 0x02;
    public static final int JAL_INSTRUCTION_OPCODE = 0x03;

    @Override
    public Instruction perform(Integer instruction) {
        dbc.precondition(instruction != null, "instruction cannot be null");
        final int opcode = (instruction.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        final int target = instruction.intValue() & (~INSTRUCTION_OPCODE_BITMASK);
        switch (opcode) {
            case J_INSTRUCTION_OPCODE:
                return new JInstruction(target);
            case JAL_INSTRUCTION_OPCODE:
                return new JalInstruction(target);
        }
        throw new UnknownInstructionException(opcode);
    }

    @Override
    public boolean accept(Integer element) {
        dbc.precondition(element != null, "element cannot be null");
        final int opcode = (element.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        return opcode == J_INSTRUCTION_OPCODE || opcode == JAL_INSTRUCTION_OPCODE;
    }
}
