package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoder;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.NoInstructionDecoderFoundError;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.UnknownInstruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.UnknownInstructionException;

public class DefaultInstructionParser implements Delegate<Instruction, Integer> {

    private final Delegate<InstructionDecoder, Integer> instructionDecoderSelector;

    public DefaultInstructionParser(Delegate<InstructionDecoder, Integer> instructionDecoderSelector) {
        dbc.precondition(instructionDecoderSelector != null, "instructionDecoderSelector cannot be null");
        this.instructionDecoderSelector = instructionDecoderSelector;
    }

    @Override
    public Instruction perform(Integer opcode) {
        InstructionDecoder decoder;
        try {
            decoder = instructionDecoderSelector.perform(opcode);
        } catch (NoInstructionDecoderFoundError ex) {
            return new UnknownInstruction();
        }
        try {
            return decoder.perform(opcode);
        } catch (UnknownInstructionException ex) {
            return new UnknownInstruction();
        }
    }
}
