package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.OpcodeAndInstruction;

public class ToOpcodeAndInstruction implements Delegate<OpcodeAndInstruction, Pair<Integer, Instruction>> {

    @Override
    public OpcodeAndInstruction perform(Pair<Integer, Instruction> opcodeAndInstruction) {
        return OpcodeAndInstruction.create(opcodeAndInstruction.first(), opcodeAndInstruction.second());
    }
}
