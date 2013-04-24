package org.bitbucket.theimplementer.mipsdisassembler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class PsxExecutableParser implements Delegate<PsxExecutable, InputStream> {

    @Override
    public PsxExecutable perform(InputStream inputStream) {
        dbc.precondition(inputStream != null, "inputStream cannot be null");
        final String signature = new String(readBytes(inputStream, 8));
        if (!"PS-X EXE".equals(signature)) {
            throw new UnknownFileFormatException();
        }
        final PsxExecutable psxExecutable = new PsxExecutable();
        skipBytes(inputStream, 8);
        final Integer initialProgramCounter = parseIntAsLittleEndian(readBytes(inputStream, 4));
        psxExecutable.setInitialProgramCounter(initialProgramCounter);
        skipBytes(inputStream, 4);
        final Integer textSectionStart = parseIntAsLittleEndian(readBytes(inputStream, 4));
        psxExecutable.setTextSectionStart(textSectionStart);
        final Integer textSectionSize = parseIntAsLittleEndian(readBytes(inputStream, 4));
        psxExecutable.setTextSectionSize(textSectionSize);
        skipBytes(inputStream, 16);
        final Integer initialStackPointer = parseIntAsLittleEndian(readBytes(inputStream, 4));
        psxExecutable.setInitialStackPointer(initialStackPointer);
        skipBytes(inputStream, 1996);
        psxExecutable.setTextSection(readBytes(inputStream, textSectionSize));
        return psxExecutable;
    }

    private void skipBytes(InputStream inputStream, int number) {
        try {
            inputStream.skip(number);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private byte[] readBytes(InputStream inputStream, int size) {
        final byte buffer[] = new byte[size];
        try {
            inputStream.read(buffer);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return buffer;
    }

    private Integer parseIntAsLittleEndian(byte[] buffer) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.put(buffer).order(ByteOrder.LITTLE_ENDIAN).rewind();
        return byteBuffer.getInt();
    }
}
