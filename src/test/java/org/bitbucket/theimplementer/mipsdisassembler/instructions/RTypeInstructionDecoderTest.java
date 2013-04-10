package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.junit.Assert;
import org.junit.Test;

public class RTypeInstructionDecoderTest {

    private final RTypeInstructionDecoder instance = new RTypeInstructionDecoder();

    @Test
    public void acceptsRTypeInstructions() {
        final boolean got = instance.accept(0);
        Assert.assertEquals(true, got);
    }

    @Test
    public void rejectsOtherInstructions() {
        final boolean got = instance.accept(0x10000000);
        Assert.assertEquals(false, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptThrowsWithNullInstruction() {
        instance.accept(null);
    }

    @Test
    public void performYieldsInstructionIfSupported() {
        final Instruction got = instance.perform(RTypeInstructionDecoder.ADD_INSTRUCTION_FUNCTION);
        Assert.assertNotNull(got);
    }

    @Test
    public void canDecodeAddInstructions() {
        final AddInstruction expected = new AddInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.ADD_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }
    
    @Test
    public void canDecodeAdduInstructions() {
        final AdduInstruction expected = new AdduInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.ADDU_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }
    
    @Test
    public void canDecodeSllInstructions() {
        final SllInstruction expected = new SllInstruction(Register.ZR, Register.ZR, 0);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLL_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
        
    }

    @Test
    public void canDecodeSrlInstructions() {
        final SrlInstruction expected = new SrlInstruction(Register.ZR, Register.ZR, 0);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SRL_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeSraInstructions() {
        final SraInstruction expected = new SraInstruction(Register.ZR, Register.ZR, 0);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SRA_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeSllvInstructions() {
        final SllvInstruction expected = new SllvInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLLV_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeSrlvInstructions() {
        final SrlvInstruction expected = new SrlvInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SRLV_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeSravInstructions() {
        final SravInstruction expected = new SravInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SRAV_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeJrInstructions() {
        final JrInstruction expected = new JrInstruction(Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.JR_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeJalrInstructions() {
        final JalrInstruction expected = new JalrInstruction(Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.JALR_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeSyscallInstructions() {
        final SyscallInstruction expected = new SyscallInstruction();
        final Instruction got = instance.perform(RTypeInstructionDecoder.SYSCALL_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }

    @Test
    public void canDecodeBreakInstructions() {
        final BreakInstruction expected = new BreakInstruction();
        final Instruction got = instance.perform(RTypeInstructionDecoder.BREAK_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);

    }
    
    @Test(expected = UnknownInstructionException.class)
    public void performThrowsIfInstructionIsNotSupported() {
        instance.perform(0xff);
    }
}
