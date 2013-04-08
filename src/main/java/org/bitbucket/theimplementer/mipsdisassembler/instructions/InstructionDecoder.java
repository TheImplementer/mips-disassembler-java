package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public interface InstructionDecoder extends Delegate<Instruction, Integer>, Selectable<Integer> {
}
