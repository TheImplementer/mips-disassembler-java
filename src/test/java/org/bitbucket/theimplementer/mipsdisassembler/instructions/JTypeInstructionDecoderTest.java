package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.junit.Assert;
import org.junit.Test;

public class JTypeInstructionDecoderTest {

    public static final JTypeInstructionDecoder instance = new JTypeInstructionDecoder();

    @Test
    public void acceptsJTypeInstructions() {
        final boolean got = instance.accept(JTypeInstructionDecoder.J_INSTRUCTION_OPCODE << 26);
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
    public void performYieldsInstructionIfSupported() {
        final Instruction got = instance.perform(JTypeInstructionDecoder.J_INSTRUCTION_OPCODE << 26);
        Assert.assertNotNull(got);
    }

    @Test
    public void performYieldsJInstructionWithCorrectTarget() {
        final JInstruction expected = new JInstruction(3);
        final Instruction got = instance.perform(new Integer((JTypeInstructionDecoder.J_INSTRUCTION_OPCODE << 26) | 3));
        Assert.assertEquals(expected, got);
    }
    
    @Test
    public void performYieldsJalInstructionWithCorrectTarget() {
        final JalInstruction expected = new JalInstruction(3);
        final Instruction got = instance.perform(new Integer((JTypeInstructionDecoder.JAL_INSTRUCTION_OPCODE << 26) | 3));
        Assert.assertEquals(expected, got);
    }
    
    @Test(expected = UnknownInstructionException.class)
    public void performThrowsWithAnUnsupportedInstruction() {
        instance.perform(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInstruction() {
        instance.perform(null);
    }
}
