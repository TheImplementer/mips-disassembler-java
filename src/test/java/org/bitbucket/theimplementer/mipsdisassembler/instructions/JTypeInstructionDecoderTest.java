package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.junit.Assert;
import org.junit.Test;

public class JTypeInstructionDecoderTest {

    public static final JTypeInstructionDecoder instance = new JTypeInstructionDecoder();

    @Test
    public void acceptsJTypeInstructions() {
        final boolean got = instance.accept(JTypeInstructionDecoder.JTYPE_INSTRUCTION_SIGNATURE);
        Assert.assertEquals(true, got);
    }

    @Test
    public void rejectsOtherInstructions() {
        final boolean got = instance.accept(0);
        Assert.assertEquals(false, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptThrowsWithNullElement() {
        instance.accept(null);
    }

    @Test
    public void performYieldsInstruction() {
        final Instruction got = instance.perform(0);
        Assert.assertNotNull(got);
    }

    @Test
    public void performYieldsJInstructionWithCorrectTarget() {
        final JInstruction expected = new JInstruction(3);
        final Instruction got = instance.perform(new Integer(JTypeInstructionDecoder.JTYPE_INSTRUCTION_SIGNATURE | 3));
        Assert.assertEquals(expected, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInstruction() {
        instance.perform(null);
    }
}
