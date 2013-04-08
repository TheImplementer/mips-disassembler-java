package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public interface Selectable<T> {

    boolean accept(T element);
}
