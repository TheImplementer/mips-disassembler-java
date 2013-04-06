package org.bitbucket.theimplementer.mipsdisassembler;

public interface Selectable<T> {

    boolean accept(T element);
}
