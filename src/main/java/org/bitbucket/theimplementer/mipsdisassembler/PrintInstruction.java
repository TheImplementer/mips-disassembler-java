package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.actions.Action;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

public class PrintInstruction implements Action<Instruction> {

    @Override
    public void perform(Instruction instruction) {
        System.out.println(instruction);
    }
}
