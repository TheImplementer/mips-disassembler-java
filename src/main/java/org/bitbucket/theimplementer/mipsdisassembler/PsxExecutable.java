package org.bitbucket.theimplementer.mipsdisassembler;

public class PsxExecutable {

    private int initialProgramCounter;
    private int textSectionStart;
    private int textSectionSize;
    private int initialStackPointer;
    private byte[] textSection;

    public int getInitialProgramCounter() {
        return initialProgramCounter;
    }

    public void setInitialProgramCounter(int initialProgramCounter) {
        this.initialProgramCounter = initialProgramCounter;
    }

    public int getTextSectionStart() {
        return textSectionStart;
    }

    public void setTextSectionStart(int textSectionStart) {
        this.textSectionStart = textSectionStart;
    }

    public int getTextSectionSize() {
        return textSectionSize;
    }

    public void setTextSectionSize(int textSectionSize) {
        this.textSectionSize = textSectionSize;
    }

    public int getInitialStackPointer() {
        return initialStackPointer;
    }

    public void setInitialStackPointer(int initialStackPointer) {
        this.initialStackPointer = initialStackPointer;
    }

    public byte[] getTextSection() {
        return textSection;
    }

    public void setTextSection(byte[] textSectionStream) {
        this.textSection = textSectionStream;
    }
}
