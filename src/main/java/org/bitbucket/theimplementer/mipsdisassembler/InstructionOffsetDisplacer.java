package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.OpcodeAndInstruction;

public class InstructionOffsetDisplacer implements Delegate<Pair<Integer, OpcodeAndInstruction>, Pair<Integer, OpcodeAndInstruction>> {

    private static final Integer OFFSET_DISPLACEMENT = 0x80010000;

    @Override
    public Pair<Integer, OpcodeAndInstruction> perform(Pair<Integer, OpcodeAndInstruction> offsetAndInstruction) {
        final Integer offset = offsetAndInstruction.first();
        return Pair.of(offset + OFFSET_DISPLACEMENT, offsetAndInstruction.second());
    }
}
