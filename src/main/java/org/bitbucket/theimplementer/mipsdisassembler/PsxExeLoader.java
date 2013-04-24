package org.bitbucket.theimplementer.mipsdisassembler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PsxExeLoader implements FileLoader<PsxExecutable> {

    @Override
    public PsxExecutable load(File file) throws FileNotFoundException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        return new PsxExecutableParser().perform(fileInputStream);
    }
}
