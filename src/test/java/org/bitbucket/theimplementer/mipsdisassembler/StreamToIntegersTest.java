package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class StreamToIntegersTest {

    private final StreamToIntegers instance = new StreamToIntegers();

    @Test(expected = IllegalArgumentException.class)
    public void performThrowsWithNullInputStream() {
        instance.perform(null);
    }

    @Test
    public void performWithEmptyInputStreamYieldsEmptyList() {
        final List<Pair<Integer, Integer>> got = instance.perform(new ByteArrayInputStream(new byte[]{}));
        Assert.assertEquals(0, got.size());
    }

    @Test
    public void performWithInputStreamWithSizeLessThan4YieldsEmptyList() {
        final byte[] streamContent = {0, 0, 0};
        final List<Pair<Integer, Integer>> got = instance.perform(new ByteArrayInputStream(streamContent));
        Assert.assertEquals(0, got.size());
    }

    @Test
    public void performYieldsExpectedOffset() {
        final byte[] streamContent = {0, 0, 0, 0, 1, 0, 0, 0};
        final Pair<Integer, Integer> got = Consumers.nth(2, instance.perform(new ByteArrayInputStream(streamContent)));
        Assert.assertEquals(Integer.valueOf(4), got.first());
    }

    @Test
    public void performYieldsExpectedOpcode() {
        final byte[] streamContent = {0, 0, 0, 0};
        final Pair<Integer, Integer> got = Consumers.one(instance.perform(new ByteArrayInputStream(streamContent)));
        Assert.assertEquals(Integer.valueOf(0), got.second());
    }

    @Test
    public void performParsesInputStreamBytesAsLittleEndian() {
        final byte[] streamContent = {1, 0, 0, 0};
        final Pair<Integer, Integer> got = Consumers.one(instance.perform(new ByteArrayInputStream(streamContent)));
        Assert.assertEquals(Integer.valueOf(1), got.second());
    }
}
