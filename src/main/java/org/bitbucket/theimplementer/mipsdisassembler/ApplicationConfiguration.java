package org.bitbucket.theimplementer.mipsdisassembler;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.Instruction;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoder;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.InstructionDecoderSelector;
import org.bitbucket.theimplementer.mipsdisassembler.instructions.JTypeInstructionDecoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public InstructionDecoder jTypeInstructionDecoder() {
        return new JTypeInstructionDecoder();
    }

    @Bean
    public Delegate<InstructionDecoder, Integer> instructionDecoderSelector(List<InstructionDecoder> instructionDecoders) {
        return new InstructionDecoderSelector(instructionDecoders);
    }

    @Bean
    public Delegate<Instruction, Integer> instructionParser(@Qualifier("instructionDecoderSelector") Delegate<InstructionDecoder, Integer> instructionDecoderSelector) {
        return new DefaultInstructionParser(instructionDecoderSelector);
    }

    @Bean
    public Delegate<List<Instruction>, InputStream> mipsDisassembler(@Qualifier("instructionParser") Delegate<Instruction, Integer> instructionParser) {
        return new DefaultMipsDisassembler(instructionParser);
    }
}
