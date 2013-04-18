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
    public void canDecodeNopInstructions() {
        final NopInstruction expected = new NopInstruction();
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLL_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }
    
    @Test
    public void canDecodeSllInstructions() {
        final SllInstruction expected = new SllInstruction(Register.ZR, Register.ZR, 20);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLL_INSTRUCTION_FUNCTION | 0x540);
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

    @Test
    public void canDecodeMfhiInstructions() {
        final MfhiInstruction expected = new MfhiInstruction(Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MFHI_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeMthiInstructions() {
        final MthiInstruction expected = new MthiInstruction(Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MTHI_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeMfloInstructions() {
        final MfloInstruction expected = new MfloInstruction(Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MFLO_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeMtloInstructions() {
        final MtloInstruction expected = new MtloInstruction(Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MTLO_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeMultInstructions() {
        final MultInstruction expected = new MultInstruction(Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MULT_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeMultuInstructions() {
        final MultuInstruction expected = new MultuInstruction(Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.MULTU_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeDivInstructions() {
        final DivInstruction expected = new DivInstruction(Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.DIV_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeDivuInstructions() {
        final DivuInstruction expected = new DivuInstruction(Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.DIVU_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSubInstructions() {
        final SubInstruction expected = new SubInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SUB_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSubuInstructions() {
        final SubuInstruction expected = new SubuInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SUBU_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeAndInstructions() {
        final AndInstruction expected = new AndInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.AND_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeOrInstructions() {
        final OrInstruction expected = new OrInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.OR_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeXorInstructions() {
        final XorInstruction expected = new XorInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.XOR_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeNorInstructions() {
        final NorInstruction expected = new NorInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.NOR_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSltInstructions() {
        final SltInstruction expected = new SltInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLT_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSltuInstructions() {
        final SltuInstruction expected = new SltuInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.SLTU_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }


    @Test(expected = UnknownInstructionException.class)
    public void performThrowsIfInstructionIsNotSupported() {
        instance.perform(0xff);
    }
}
