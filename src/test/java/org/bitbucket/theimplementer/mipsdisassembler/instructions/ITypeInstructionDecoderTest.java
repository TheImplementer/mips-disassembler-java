package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.junit.Assert;
import org.junit.Test;

public class ITypeInstructionDecoderTest {

    private final ITypeInstructionDecoder instance = new ITypeInstructionDecoder();

    @Test
    public void acceptsITypeInstructions() {
        final boolean got = instance.accept(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(true, got);
    }

    @Test
    public void rejectsOtherInstructions() {
        final boolean got = instance.accept(0);
        Assert.assertEquals(false, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptThrowsWithNullInstruction() {
        instance.accept(null);
    }

    @Test
    public void performYieldsInstructionIfSupported() {
        final Instruction got = instance.perform(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertNotNull(got);
    }

    @Test
    public void canDecodeAddiInstructions() {
        final AddiInstruction expected = new AddiInstruction(Register.ZR, Register.ZR, (short)0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }
    
    @Test(expected = UnknownInstructionException.class)
    public void performThrowsIfInstructionIsNotSupported() {
        final int unsupportedOpcode = 0xff;
        instance.perform(unsupportedOpcode << 26);
    }
}
