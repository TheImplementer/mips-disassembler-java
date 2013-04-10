package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class RTypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_OPCODE_BITMASK = 0xfc000000;
    public static final int RTYPE_FUNCTION_BITMASK = 0x3f;
    public static final int RD_REGISTER_BITMASK = 0xf800;
    public static final int RT_REGISTER_BITMASK = 0x1f0000;
    public static final int RS_REGISTER_BITMASK = 0x3e00000;
    public static final int SHIFT_AMOUNT_BITMASK = 0x7a0;

    public static final int ADD_INSTRUCTION_FUNCTION = 0x20;
    public static final int ADDU_INSTRUCTION_FUNCTION = 0x21;
    public static final int SLL_INSTRUCTION_FUNCTION = 0x00;

    @Override
    public Instruction perform(Integer instruction) {
        final int function = instruction.intValue() & RTYPE_FUNCTION_BITMASK;
        final int shiftAmount = (instruction.intValue() & SHIFT_AMOUNT_BITMASK) >> 6;
        final Register rdRegister = Register.fromValue((instruction.intValue() & RD_REGISTER_BITMASK) >> 11);
        final Register rtRegister = Register.fromValue((instruction.intValue() & RT_REGISTER_BITMASK) >> 16);
        final Register rsRegister = Register.fromValue((instruction.intValue() & RS_REGISTER_BITMASK) >> 21);
        switch (function) {
            case ADD_INSTRUCTION_FUNCTION:
                return new AddInstruction(rdRegister, rsRegister, rtRegister);
            case ADDU_INSTRUCTION_FUNCTION:
                return new AdduInstruction(rdRegister, rsRegister, rsRegister);
            case SLL_INSTRUCTION_FUNCTION:
                return new SllInstruction(rdRegister, rsRegister, shiftAmount);
        }

        throw new UnknownInstructionException(function);
    }

    @Override
    public boolean accept(Integer element) {
        dbc.precondition(element != null, "element cannot be null");
        final int opcode = (element.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        return opcode == 0;
    }
}
