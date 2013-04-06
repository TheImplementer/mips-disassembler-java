package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.Searches;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Maybe;

import java.util.List;

public class InstructionDecoderSelector implements Delegate<InstructionDecoder, Integer> {

    private final List<InstructionDecoder> instructionDecoders;

    public InstructionDecoderSelector(List<InstructionDecoder> instructionDecoders) {
        dbc.precondition(instructionDecoders != null, "instructionDecoders cannot be null");
        this.instructionDecoders = instructionDecoders;
    }

    @Override
    public InstructionDecoder perform(final Integer instruction) {
        final Maybe<InstructionDecoder> maybeDecoder = Searches.searchOne(instructionDecoders, new Predicate<InstructionDecoder>() {
            @Override
            public boolean accept(InstructionDecoder instructionDecoder) {
                return instructionDecoder.accept(instruction);
            }
        });
        if (!maybeDecoder.hasValue()) {
            throw new RuntimeException(String.format("cannot find a decoder for specified instruction: %s", Integer.toHexString(instruction)));
        }
        return maybeDecoder.value();
    }
}
