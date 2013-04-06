package org.bitbucket.theimplementer.mipsdisassembler;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import net.emaze.dysfunctional.Consumers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StreamToIntegersTest {

    private final StreamToIntegers instance = new StreamToIntegers();

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInputStream() {
        instance.perform(null);
    }

    @Test
    public void performWithEmptyInputStreamYieldsEmptyList() {
        final List<Integer> got = instance.perform(new ByteInputStream());
        Assert.assertEquals(0, got.size());
    }

    @Test
    public void performWithInputStreamWithSizeLessThan4YieldsEmptyList() {
        final byte[] streamContent = {0, 0, 0};
        final List<Integer> got = instance.perform(new ByteInputStream(streamContent, 3));
        Assert.assertEquals(0, got.size());
    }

    @Test
    public void performYieldsExpected() {
        final byte[] streamContent = {0, 0, 0, 0};
        final Integer got = Consumers.one(instance.perform(new ByteInputStream(streamContent, 4)));
        Assert.assertEquals(Integer.valueOf(0), got);
    }

    @Test
    public void performParsesInputStreamBytesAsLittleEndian() {
        final byte[] streamContent = {0, 0, 0, 1};
        final Integer got = Consumers.one(instance.perform(new ByteInputStream(streamContent, 4)));
        Assert.assertEquals(Integer.valueOf(1), got);
    }
}
