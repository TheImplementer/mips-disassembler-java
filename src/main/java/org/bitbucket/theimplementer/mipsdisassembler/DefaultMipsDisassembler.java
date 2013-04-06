package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

import java.io.InputStream;
import java.util.List;

public class DefaultMipsDisassembler implements Delegate<List<Instruction>, InputStream> {

    private final Delegate<Instruction, Integer> instructionParser;

    public DefaultMipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        dbc.precondition(instructionParser != null, "instructionParser cannot be null");
        this.instructionParser = instructionParser;
    }

    @Override
    public List<Instruction> perform(InputStream stream) {
        final List<Integer> instructionsAsIntegers = new StreamToIntegers().perform(stream);
        return Applications.map(instructionsAsIntegers, instructionParser);
    }

}
