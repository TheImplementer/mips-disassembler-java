package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public class OpcodeAndInstruction {
    private Integer opcode;
    private Instruction instruction;

    public static OpcodeAndInstruction create(Integer opcode, Instruction instruction) {
        final OpcodeAndInstruction entity = new OpcodeAndInstruction();
        entity.setOpcode(opcode);
        entity.setInstruction(instruction);
        return entity;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public Integer getOpcode() {
        return opcode;
    }

    public void setOpcode(Integer opcode) {
        this.opcode = opcode;
    }
}
