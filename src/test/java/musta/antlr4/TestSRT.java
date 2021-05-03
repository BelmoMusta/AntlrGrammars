package musta.antlr4;

import musta.SrtLexer;
import musta.SrtParser;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.FileInputStream;

public class TestSRT {
	
	@Test
	public void whenLogContainsOneErrorLogEntryThenOneErrorIsReturned()
			throws Exception {
		SrtLexer srtLexer = new SrtLexer(CharStreams.fromStream(new FileInputStream("D:\\0001_PERSO\\entertainment" +
				"\\Parallel.2020.HDRip.XviD" +
				".AC3-EVO-English" +
				".srt")));
		SrtParser logsParser = new SrtParser(new BufferedTokenStream(srtLexer));
		
		SrtListenerImpl listener = new SrtListenerImpl();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, logsParser.srt());
	}
}
