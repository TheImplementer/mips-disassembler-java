package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

import java.io.InputStream;
import java.util.List;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Zips;
import net.emaze.dysfunctional.tuples.Pair;

public class DefaultMipsDisassembler implements Delegate<List<Pair<Integer, Instruction>>, InputStream> {

    private final Delegate<Instruction, Integer> instructionParser;

    public DefaultMipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        dbc.precondition(instructionParser != null, "instructionParser cannot be null");
        this.instructionParser = instructionParser;
    }

    @Override
    public List<Pair<Integer, Instruction>> perform(InputStream stream) {
        final List<Integer> instructionsAsIntegers = new StreamToIntegers().perform(stream);
        final List<Instruction> instructions = Applications.map(instructionsAsIntegers, instructionParser);
        return Consumers.all(Zips.shortest(instructionsAsIntegers, instructions));
    }
}
