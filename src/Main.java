import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileReader;
import java.io.IOException;

public class Main extends CSVBaseListener {
    public static void main(String[] args) throws IOException {
        // Get CSV lexer
        CSVLexer lexer = new CSVLexer(new ANTLRInputStream(new FileReader("Test.csv")));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        CSVParser parser = new CSVParser(tokens);

        // Specify our entry point
        CSVParser.FileContext fileContext = parser.file();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        CSVBaseListener listener = new Main();
        walker.walk(listener, fileContext);
    }

    public void exitFile(CSVParser.FileContext ctx) {
        System.out.println("If no error outputs occured, then file has valid format!");
        System.out.println(ctx.getText());
    }

}
