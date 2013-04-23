package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.Consumers;
import net.emaze.dysfunctional.Zips;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.tuples.Pair;
import net.emaze.dysfunctional.tuples.PairFirst;
import net.emaze.dysfunctional.tuples.PairSecond;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.OpcodeAndInstruction;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class DefaultMipsDisassembler implements Delegate<List<Pair<Integer, OpcodeAndInstruction>>, InputStream> {

    private final Delegate<Instruction, Integer> instructionParser;

    public DefaultMipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        dbc.precondition(instructionParser != null, "instructionParser cannot be null");
        this.instructionParser = instructionParser;
    }

    @Override
    public List<Pair<Integer, OpcodeAndInstruction>> perform(InputStream stream) {
        final List<Pair<Integer, Integer>> offsetsAndOpcodes = new StreamToIntegers().perform(stream);
        final List<Integer> opcodes = Applications.map(offsetsAndOpcodes, new PairSecond<Integer, Integer>());
        final List<Instruction> instructions = Applications.map(opcodes, instructionParser);
        final Iterator<OpcodeAndInstruction> opcodesAndInstructions = Applications.transform(Zips.shortest(opcodes, instructions), new ToOpcodeAndInstruction());
        final Iterator<Integer> offsets = Applications.transform(offsetsAndOpcodes, new PairFirst<Integer, Integer>());
        return Consumers.all(Zips.shortest(offsets, opcodesAndInstructions));
    }

}
