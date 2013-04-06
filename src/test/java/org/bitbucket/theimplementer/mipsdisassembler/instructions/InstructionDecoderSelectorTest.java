package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class InstructionDecoderSelectorTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingInstanceWithNullInstructionDecodersThrows() {
        new InstructionDecoderSelector(null);
    }

    @Test(expected = RuntimeException.class)
    public void performingOnAnUnsupportedInstructionThrows() {
        new InstructionDecoderSelector(Collections.<InstructionDecoder>emptyList()).perform(0);
    }

    @Test
    public void performSelectsDecoder() {
        final InstructionDecoder got = new InstructionDecoderSelector(Arrays.<InstructionDecoder>asList(new AlwaysAcceptingInstructionDecoder())).perform(0);
        Assert.assertNotNull(got);
    }

    public static class AlwaysAcceptingInstructionDecoder implements InstructionDecoder {

        @Override
        public Instruction perform(Integer integer) {
            return null;
        }

        @Override
        public boolean accept(Integer element) {
            return true;
        }
    }

}
