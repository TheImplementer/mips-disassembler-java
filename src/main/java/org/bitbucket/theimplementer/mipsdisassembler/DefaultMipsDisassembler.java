package org.bitbucket.theimplementer.mipsdisassembler;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;

import java.util.List;

public class DefaultMipsDisassembler implements Delegate<List<Instruction>, ByteInputStream> {

    private final Delegate<Instruction, Integer> instructionParser;

    public DefaultMipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        dbc.precondition(instructionParser != null, "instructionParser cannot be null");
        this.instructionParser = instructionParser;
    }

    @Override
    public List<Instruction> perform(ByteInputStream stream) {
        final List<Integer> instructionsAsIntegers = new StreamToIntegers().perform(stream);
        return Applications.map(instructionsAsIntegers, instructionParser);
    }

}
