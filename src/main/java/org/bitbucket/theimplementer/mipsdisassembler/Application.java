package org.bitbucket.theimplementer.mipsdisassembler;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import net.emaze.dysfunctional.Applications;
import net.emaze.dysfunctional.Casts;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final Delegate<List<Instruction>, ByteArrayInputStream> mipsDisassembler = Casts.widen(context.getBean("mipsDisassembler"));
        final List<Instruction> instructions = mipsDisassembler.perform(new ByteInputStream());
        Applications.each(instructions, new PrintInstruction());
    }

}