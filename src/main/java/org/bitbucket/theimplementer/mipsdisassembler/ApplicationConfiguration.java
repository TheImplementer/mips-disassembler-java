package org.bitbucket.theimplementer.mipsdisassembler;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoder;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoderSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

    public Delegate<InstructionDecoder, Integer> instructionDecoderSelector(List<InstructionDecoder> instructionDecoders) {
        return new InstructionDecoderSelector(instructionDecoders);
    }

    @Bean
    public Delegate<Instruction, Integer> instructionParser(Delegate<InstructionDecoder, Integer> instructionDecoderSelector) {
        return new DefaultInstructionParser(instructionDecoderSelector);
    }

    @Bean
    public Delegate<List<Instruction>, ByteInputStream> mipsDisassembler(Delegate<Instruction, Integer> instructionParser) {
        return new DefaultMipsDisassembler(instructionParser);
    }
}
