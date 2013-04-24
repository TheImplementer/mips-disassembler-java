package org.bitbucket.theimplementer.mipsdisassembler;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;

public class PsxExecutableParserTest {

    private final PsxExecutableParser instance = new PsxExecutableParser();
    private final byte[] fakeExecutable = new StringBuilder().
            append("PS-X EXE\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000").
            append("\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0002\u0000\u0000\u0000\u0003\u0000\u0000\u0000").
            append("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000").
            append("\u0004\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000").
            toString().getBytes();
        

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInputStream() {
        instance.perform(null);
    }

    @Test(expected = UnknownFileFormatException.class)
    public void performThrowsWithUnknownFileFormat() {
        instance.perform(new ByteArrayInputStream(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void performYieldsExecutableForPsxExecutableFiles() {
        final PsxExecutable got = instance.perform(new ByteArrayInputStream(fakeExecutable));
        Assert.assertNotNull(got);
    }

    @Test
    public void canParseInitialProgramCounterAsLittleEndian() {
        final PsxExecutable got = instance.perform(new ByteArrayInputStream(fakeExecutable));
        Assert.assertEquals(1, got.getInitialProgramCounter());
    }

    @Test
    public void canParseTextSectionStartAsLittleEndian() {
        final PsxExecutable got = instance.perform(new ByteArrayInputStream(fakeExecutable));
        Assert.assertEquals(2, got.getTextSectionStart());
    }

    @Test
    public void canParseTextSectionSizeAsLittleEndian() {
        final PsxExecutable got = instance.perform(new ByteArrayInputStream(fakeExecutable));
        Assert.assertEquals(3, got.getTextSectionSize());
    }

    @Test
    public void canParseInitialStackPointerAsLittleEndian() {
        final PsxExecutable got = instance.perform(new ByteArrayInputStream(fakeExecutable));
        Assert.assertEquals(4, got.getInitialStackPointer());
    }
}