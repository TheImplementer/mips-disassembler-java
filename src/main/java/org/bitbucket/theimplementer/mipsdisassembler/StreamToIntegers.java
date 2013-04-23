package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

public class StreamToIntegers implements Delegate<List<Pair<Integer, Integer>>, InputStream> {

    @Override
    public List<Pair<Integer, Integer>> perform(InputStream inputStream) {
        dbc.precondition(inputStream != null, "inputStream cannot be null");
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        int offset = 0;
        final List<Pair<Integer, Integer>> offsetsAndIntegers = new LinkedList<>();
        while (true) {
            try {
                final Integer integer = Integer.valueOf(dataInputStream.readInt());
                final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                byteBuffer.order(ByteOrder.BIG_ENDIAN).putInt(integer).order(ByteOrder.LITTLE_ENDIAN).rewind();
                offsetsAndIntegers.add(Pair.of(offset, byteBuffer.getInt()));
                offset += 4;
            } catch (IOException e) {
                break;
            }
        }

        return offsetsAndIntegers;
    }
}
