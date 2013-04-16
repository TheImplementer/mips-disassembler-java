package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class RTypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_OPCODE_BITMASK = 0xfc000000;
    public static final int RTYPE_FUNCTION_BITMASK = 0x3f;
    public static final int RD_REGISTER_BITMASK = 0xf800;
    public static final int RT_REGISTER_BITMASK = 0x1f0000;
    public static final int RS_REGISTER_BITMASK = 0x3e00000;
    public static final int SHIFT_AMOUNT_BITMASK = 0x7a0;

    public static final int SLL_INSTRUCTION_FUNCTION = 0x00;
    public static final int SRL_INSTRUCTION_FUNCTION = 0x02;
    public static final int SRA_INSTRUCTION_FUNCTION = 0x03;
    public static final int SLLV_INSTRUCTION_FUNCTION = 0x04;
    public static final int SRLV_INSTRUCTION_FUNCTION = 0x06;
    public static final int SRAV_INSTRUCTION_FUNCTION = 0x07;
    public static final int JR_INSTRUCTION_FUNCTION = 0x08;
    public static final int JALR_INSTRUCTION_FUNCTION = 0x09;
    public static final int SYSCALL_INSTRUCTION_FUNCTION = 0x0c;
    public static final int BREAK_INSTRUCTION_FUNCTION = 0x0d;
    public static final int MFHI_INSTRUCTION_FUNCTION = 0x10;
    public static final int MTHI_INSTRUCTION_FUNCTION = 0x11;
    public static final int MFLO_INSTRUCTION_FUNCTION = 0x12;
    public static final int MTLO_INSTRUCTION_FUNCTION = 0x13;
    public static final int MULT_INSTRUCTION_FUNCTION = 0x18;
    public static final int MULTU_INSTRUCTION_FUNCTION = 0x19;
    public static final int DIV_INSTRUCTION_FUNCTION = 0x1a;
    public static final int DIVU_INSTRUCTION_FUNCTION = 0x1b;
    public static final int ADD_INSTRUCTION_FUNCTION = 0x20;
    public static final int ADDU_INSTRUCTION_FUNCTION = 0x21;
    public static final int SUB_INSTRUCTION_FUNCTION = 0x22;
    public static final int SUBU_INSTRUCTION_FUNCTION = 0x23;
    public static final int AND_INSTRUCTION_FUNCTION = 0x24;
    public static final int OR_INSTRUCTION_FUNCTION = 0x25;
    public static final int XOR_INSTRUCTION_FUNCTION = 0x26;
    public static final int NOR_INSTRUCTION_FUNCTION = 0x27;
    public static final int SLT_INSTRUCTION_FUNCTION = 0x2a;
    public static final int SLTU_INSTRUCTION_FUNCTION = 0x2b;

    @Override
    public Instruction perform(Integer instruction) {
        final int function = instruction.intValue() & RTYPE_FUNCTION_BITMASK;
        final int shiftAmount = (instruction.intValue() & SHIFT_AMOUNT_BITMASK) >> 6;
        final Register rdRegister = Register.fromValue((instruction.intValue() & RD_REGISTER_BITMASK) >> 11);
        final Register rtRegister = Register.fromValue((instruction.intValue() & RT_REGISTER_BITMASK) >> 16);
        final Register rsRegister = Register.fromValue((instruction.intValue() & RS_REGISTER_BITMASK) >> 21);
        switch (function) {
            case SLL_INSTRUCTION_FUNCTION:
                return new SllInstruction(rdRegister, rtRegister, shiftAmount);
            case SRL_INSTRUCTION_FUNCTION:
                return new SrlInstruction(rdRegister, rtRegister, shiftAmount);
            case SRA_INSTRUCTION_FUNCTION:
                return new SraInstruction(rdRegister, rtRegister, shiftAmount);
            case SLLV_INSTRUCTION_FUNCTION:
                return new SllvInstruction(rdRegister, rtRegister, rsRegister);
            case SRLV_INSTRUCTION_FUNCTION:
                return new SrlvInstruction(rdRegister, rtRegister, rsRegister);
            case SRAV_INSTRUCTION_FUNCTION:
                return new SravInstruction(rdRegister, rtRegister, rsRegister);
            case JR_INSTRUCTION_FUNCTION:
                return new JrInstruction(rsRegister);
            case JALR_INSTRUCTION_FUNCTION:
                return new JalrInstruction(rdRegister, rsRegister);
            case SYSCALL_INSTRUCTION_FUNCTION:
                return new SyscallInstruction();
            case BREAK_INSTRUCTION_FUNCTION:
                return new BreakInstruction();
            case MFHI_INSTRUCTION_FUNCTION:
                return new MfhiInstruction(rdRegister);
            case MTHI_INSTRUCTION_FUNCTION:
                return new MthiInstruction(rsRegister);
            case MFLO_INSTRUCTION_FUNCTION:
                return new MfloInstruction(rdRegister);
            case MTLO_INSTRUCTION_FUNCTION:
                return new MtloInstruction(rsRegister);
            case MULT_INSTRUCTION_FUNCTION:
                return new MultInstruction(rsRegister, rtRegister);
            case MULTU_INSTRUCTION_FUNCTION:
                return new MultuInstruction(rsRegister, rtRegister);
            case DIV_INSTRUCTION_FUNCTION:
                return new DivInstruction(rsRegister, rtRegister);
            case DIVU_INSTRUCTION_FUNCTION:
                return new DivuInstruction(rsRegister, rtRegister);
            case ADD_INSTRUCTION_FUNCTION:
                return new AddInstruction(rdRegister, rsRegister, rtRegister);
            case ADDU_INSTRUCTION_FUNCTION:
                return new AdduInstruction(rdRegister, rsRegister, rtRegister);
            case SUB_INSTRUCTION_FUNCTION:
                return new SubInstruction(rdRegister, rsRegister, rtRegister);
            case SUBU_INSTRUCTION_FUNCTION:
                return new SubuInstruction(rdRegister, rsRegister, rtRegister);
            case AND_INSTRUCTION_FUNCTION:
                return new AndInstruction(rdRegister, rsRegister, rtRegister);
            case OR_INSTRUCTION_FUNCTION:
                return new OrInstruction(rdRegister, rsRegister, rtRegister);
            case XOR_INSTRUCTION_FUNCTION:
                return new XorInstruction(rdRegister, rsRegister, rtRegister);
            case NOR_INSTRUCTION_FUNCTION:
                return new NorInstruction(rdRegister, rsRegister, rtRegister);
            case SLT_INSTRUCTION_FUNCTION:
                return new SltInstruction(rdRegister, rsRegister, rtRegister);
            case SLTU_INSTRUCTION_FUNCTION:
                return new SltuInstruction(rdRegister, rsRegister, rtRegister);
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
