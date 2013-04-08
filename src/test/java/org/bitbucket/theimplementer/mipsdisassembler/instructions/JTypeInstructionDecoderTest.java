package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.bitbucket.theimplementer.mipsdisassembler.instructions.jtype.JInstruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.jtype.JalInstruction;
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
    public void performYieldsInstruction() {
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

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInstruction() {
        instance.perform(null);
    }
}
