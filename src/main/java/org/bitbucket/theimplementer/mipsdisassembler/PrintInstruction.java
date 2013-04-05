package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.actions.Action;

public class PrintInstruction implements Action<Instruction> {

    @Override
    public void perform(Instruction instruction) {
        System.out.println(instruction);
    }
}
