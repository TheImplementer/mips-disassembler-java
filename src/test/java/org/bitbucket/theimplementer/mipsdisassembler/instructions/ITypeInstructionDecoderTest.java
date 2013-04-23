package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import org.junit.Assert;
import org.junit.Test;

public class ITypeInstructionDecoderTest {

    private final ITypeInstructionDecoder instance = new ITypeInstructionDecoder();

    @Test
    public void acceptsITypeInstructions() {
        final boolean got = instance.accept(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(true, got);
    }

    @Test
    public void rejectsOtherInstructions() {
        final boolean got = instance.accept(0);
        Assert.assertEquals(false, got);
    }

    @Test(expected = IllegalArgumentException.class)
    public void acceptThrowsWithNullInstruction() {
        instance.accept(null);
    }

    @Test
    public void performYieldsInstructionIfSupported() {
        final Instruction got = instance.perform(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertNotNull(got);
    }

    @Test
    public void canDecodeBltzInstructions() {
        final BltzInstruction expected = new BltzInstruction(Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BLTZ_BGEZ_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeBgezInstructions() {
        final BgezInstruction expected = new BgezInstruction(Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BLTZ_BGEZ_INSTRUCTION_OPCODE << 26 | 0x1 << 16);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeBeqInstructions() {
        final BeqInstruction expected = new BeqInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BEQ_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeBneInstructions() {
        final BneInstruction expected = new BneInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BNE_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }


    @Test
    public void canDecodeBlezInstructions() {
        final BlezInstruction expected = new BlezInstruction(Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BLEZ_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeBgtzInstructions() {
        final BgtzInstruction expected = new BgtzInstruction(Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.BGTZ_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeAddiInstructions() {
        final AddiInstruction expected = new AddiInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.ADDI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeAddiuInstructions() {
        final AddiuInstruction expected = new AddiuInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.ADDIU_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSltiInstructions() {
        final SltiInstruction expected = new SltiInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SLTI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSltiuInstructions() {
        final SltiuInstruction expected = new SltiuInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SLTIU_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeAndiInstructions() {
        final AndiInstruction expected = new AndiInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.ANDI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeOriInstructions() {
        final OriInstruction expected = new OriInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.ORI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeXoriInstructions() {
        final XoriInstruction expected = new XoriInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.XORI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLuiInstructions() {
        final LuiInstruction expected = new LuiInstruction(Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LUI_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLbInstructions() {
        final LbInstruction expected = new LbInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LB_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLhInstructions() {
        final LhInstruction expected = new LhInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LH_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLwInstructions() {
        final LwInstruction expected = new LwInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LW_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLbuInstructions() {
        final LbuInstruction expected = new LbuInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LBU_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLhuInstructions() {
        final LhuInstruction expected = new LhuInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LHU_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSbInstructions() {
        final SbInstruction expected = new SbInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SB_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeShInstructions() {
        final ShInstruction expected = new ShInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SH_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSwInstructions() {
        final SwInstruction expected = new SwInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SW_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLwlInstructions() {
        final LwlInstruction expected = new LwlInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LWL_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeLwrInstructions() {
        final LwrInstruction expected = new LwrInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.LWR_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSwlInstructions() {
        final SwlInstruction expected = new SwlInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SWL_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test
    public void canDecodeSwrInstructions() {
        final SwrInstruction expected = new SwrInstruction(Register.ZR, Register.ZR, (short) 0);
        final Instruction got = instance.perform(ITypeInstructionDecoder.SWR_INSTRUCTION_OPCODE << 26);
        Assert.assertEquals(expected, got);
    }

    @Test(expected = UnknownInstructionException.class)
    public void performThrowsIfInstructionIsNotSupported() {
        final int unsupportedOpcode = 0xff;
        instance.perform(unsupportedOpcode << 26);
    }
}
