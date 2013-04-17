package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.contracts.dbc;

public class ITypeInstructionDecoder implements InstructionDecoder {

    public static final int INSTRUCTION_OPCODE_BITMASK = 0xfc000000;

    public static final int IMMEDIATE_BITMASK = 0xffff;
    public static final int RT_REGISTER_BITMASK = 0x1f0000;
    public static final int RS_REGISTER_BITMASK = 0x3e00000;
    public static final int BLTZ_BGEZ_INSTRUCTION_OPCODE = 0x01;
    public static final int BEQ_INSTRUCTION_OPCODE = 0x04;
    public static final int BNE_INSTRUCTION_OPCODE = 0x05;
    public static final int BLEZ_INSTRUCTION_OPCODE = 0x06;
    public static final int BGTZ_INSTRUCTION_OPCODE = 0x07;
    public static final int ADDI_INSTRUCTION_OPCODE = 0x08;
    public static final int ADDIU_INSTRUCTION_OPCODE = 0x09;
    public static final int SLTI_INSTRUCTION_OPCODE = 0x0a;
    public static final int SLTIU_INSTRUCTION_OPCODE = 0x0b;
    public static final int ANDI_INSTRUCTION_OPCODE = 0x0c;
    public static final int ORI_INSTRUCTION_OPCODE = 0x0d;
    public static final int XORI_INSTRUCTION_OPCODE = 0x0e;
    public static final int LUI_INSTRUCTION_OPCODE = 0x0f;
    public static final int LB_INSTRUCTION_OPCODE = 0x20;
    public static final int LH_INSTRUCTION_OPCODE = 0x21;
    public static final int LW_INSTRUCTION_OPCODE = 0x23;
    public static final int LBU_INSTRUCTION_OPCODE = 0x24;
    public static final int LHU_INSTRUCTION_OPCODE = 0x25;
    public static final int SB_INSTRUCTION_OPCODE = 0x28;
    public static final int SH_INSTRUCTION_OPCODE = 0x29;
    public static final int SW_INSTRUCTION_OPCODE = 0x2b;

    @Override
    public Instruction perform(Integer instruction) {
        final int opcode = (instruction.intValue() & INSTRUCTION_OPCODE_BITMASK) >>> 26;
        final short immediate = (short) (instruction.intValue() & IMMEDIATE_BITMASK);
        final Register rtRegister = Register.fromValue((instruction.intValue() & RT_REGISTER_BITMASK) >> 16);
        final Register rsRegister = Register.fromValue((instruction.intValue() & RS_REGISTER_BITMASK) >> 21);
        switch (opcode) {
            case BLTZ_BGEZ_INSTRUCTION_OPCODE:
                if (rtRegister.equals(0)) {
                    return new BltzInstruction(rsRegister, immediate);
                } else {
                    return new BgezInstruction(rsRegister, immediate);
                }
            case BEQ_INSTRUCTION_OPCODE:
                return new BeqInstruction(rsRegister, rtRegister, immediate);
            case BNE_INSTRUCTION_OPCODE:
                return new BneInstruction(rsRegister, rtRegister, immediate);
            case BLEZ_INSTRUCTION_OPCODE:
                return new BlezInstruction(rsRegister, immediate);
            case BGTZ_INSTRUCTION_OPCODE:
                return new BgtzInstruction(rsRegister, immediate);
            case ADDI_INSTRUCTION_OPCODE:
                return new AddiInstruction(rtRegister, rsRegister, immediate);
            case ADDIU_INSTRUCTION_OPCODE:
                return new AddiuInstruction(rtRegister, rsRegister, immediate);
            case SLTI_INSTRUCTION_OPCODE:
                return new SltiInstruction(rtRegister, rsRegister, immediate);
            case SLTIU_INSTRUCTION_OPCODE:
                return new SltiuInstruction(rtRegister, rsRegister, immediate);
            case ANDI_INSTRUCTION_OPCODE:
                return new AndiInstruction(rtRegister, rsRegister, immediate);
            case ORI_INSTRUCTION_OPCODE:
                return new OriInstruction(rtRegister, rsRegister, immediate);
            case XORI_INSTRUCTION_OPCODE:
                return new XoriInstruction(rtRegister, rsRegister, immediate);
            case LUI_INSTRUCTION_OPCODE:
                return new LuiInstruction(rtRegister, immediate);
            case LB_INSTRUCTION_OPCODE:
                return new LbInstruction(rtRegister, rsRegister, immediate);
            case LH_INSTRUCTION_OPCODE:
                return new LhInstruction(rtRegister, rsRegister, immediate);
            case LW_INSTRUCTION_OPCODE:
                return new LwInstruction(rtRegister, rsRegister, immediate);
            case LBU_INSTRUCTION_OPCODE:
                return new LbuInstruction(rtRegister, rsRegister, immediate);
            case LHU_INSTRUCTION_OPCODE:
                return new LhuInstruction(rtRegister, rsRegister, immediate);
            case SB_INSTRUCTION_OPCODE:
                return new SbInstruction(rtRegister, rsRegister, immediate);
            case SH_INSTRUCTION_OPCODE:
                return new ShInstruction(rtRegister, rsRegister, immediate);
            case SW_INSTRUCTION_OPCODE:
                return new SwInstruction(rtRegister, rsRegister, immediate);
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
