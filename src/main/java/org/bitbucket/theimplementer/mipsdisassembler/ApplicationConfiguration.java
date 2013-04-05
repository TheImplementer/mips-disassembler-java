package org.bitbucket.theimplementer.mipsdisassembler;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Delegate<Instruction, Integer> instructionParser() {
        return new DefaultInstructionParser();
    }

    @Bean
    public Delegate<List<Instruction>, ByteInputStream> mipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        return new DefaultMipsDisassembler(instructionParser);
    }
}
