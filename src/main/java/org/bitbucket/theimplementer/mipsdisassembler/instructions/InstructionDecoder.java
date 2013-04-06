package org.bitbucket.theimplementer.mipsdisassembler.instructions;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.Selectable;

public interface InstructionDecoder extends Delegate<Instruction, Integer>, Selectable<Integer> {
}
