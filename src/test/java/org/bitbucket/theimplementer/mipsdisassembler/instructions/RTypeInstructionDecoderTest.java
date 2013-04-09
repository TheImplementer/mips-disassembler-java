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
        final Instruction got = instance.perform(0);
        Assert.assertNotNull(got);
    }

    @Test
    public void canDecodeAddInstructions() {
        final AddInstruction expected = new AddInstruction(Register.ZR, Register.ZR, Register.ZR);
        final Instruction got = instance.perform(RTypeInstructionDecoder.ADD_INSTRUCTION_FUNCTION);
        Assert.assertEquals(expected, got);
    }
}
