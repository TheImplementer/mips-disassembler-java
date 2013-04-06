package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

public class DefaultInstructionParser implements Delegate<Instruction, Integer> {

    @Override
    public Instruction perform(Integer integer) {
        return null;
    }
}
