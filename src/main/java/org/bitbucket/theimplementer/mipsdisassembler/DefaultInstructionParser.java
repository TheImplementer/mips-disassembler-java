package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoder;

public class DefaultInstructionParser implements Delegate<Instruction, Integer> {

    private final Delegate<InstructionDecoder, Integer> instructionDecoderSelector;

    public DefaultInstructionParser(Delegate<InstructionDecoder, Integer> instructionDecoderSelector) {
        dbc.precondition(instructionDecoderSelector != null, "instructionDecoderSelector cannot be null");
        this.instructionDecoderSelector = instructionDecoderSelector;
    }

    @Override
    public Instruction perform(Integer instructionAsInteger) {
        final InstructionDecoder decoder = instructionDecoderSelector.perform(instructionAsInteger);
        return decoder.perform(instructionAsInteger);
    }
}
