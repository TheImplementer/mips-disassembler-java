package org.bitbucket.theimplementer.mipsdisassembler;

import java.io.File;
import java.io.IOException;

public interface FileLoader<T> {

    T load(File file) throws IOException;
}
