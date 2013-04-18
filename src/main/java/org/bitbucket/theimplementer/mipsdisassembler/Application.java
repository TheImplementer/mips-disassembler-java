package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import net.emaze.dysfunctional.tuples.Pair;
import net.emaze.dysfunctional.tuples.PairSecond;

public class Application {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        final FileInputStream stream = new FileInputStream("SLUS_011.56");
        stream.skip(1024);
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final Delegate<List<Pair<Integer, Instruction>>, InputStream> mipsDisassembler = Casts.widen(context.getBean("mipsDisassembler"));
        final List<Pair<Integer, Instruction>> opcodesAndInstructions = mipsDisassembler.perform(stream);
        final Iterator<Instruction> instructions = Applications.transform(opcodesAndInstructions, new PairSecond<Integer, Instruction>());
        Applications.each(instructions, new PrintInstruction());
    }

}