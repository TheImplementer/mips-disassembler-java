package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class ITypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_OPCODE_BITMASK = 0xfc000000;
    
    public static final int IMMEDIATE_BITMASK = 0xffff;
    public static final int RT_REGISTER_BITMASK = 0x1f0000;
    public static final int RS_REGISTER_BITMASK = 0x3e00000;
    public static final int ADDI_INSTRUCTION_OPCODE = 0x08;
    
    @Override
    public Instruction perform(Integer instruction) {
        final int opcode = (instruction.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        final short immediate = (short)(instruction.intValue() & IMMEDIATE_BITMASK);
        final Register rtRegister = Register.fromValue((instruction.intValue() & RT_REGISTER_BITMASK) >> 16);
        final Register rsRegister = Register.fromValue((instruction.intValue() & RS_REGISTER_BITMASK) >> 21);
        switch (opcode) {
            case ADDI_INSTRUCTION_OPCODE:
                return new AddiInstruction(rtRegister, rsRegister, immediate);
        }

        throw new UnknownInstructionException(opcode);
    }

    @Override
    public boolean accept(Integer element) {
        dbc.precondition(element != null, "element cannot be null");
        final int opcode = (element.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        return (opcode != 0) && ((opcode & 0x3e) != 2) && ((opcode & 0x3a) != 0x10);
    }
}
