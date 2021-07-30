package org.jqassistant.contrib.plugin.typescript;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ScannerTest {

    @Test
    public void scan() throws IOException {
        String source = "console.log(42);";

        String script = "import {Parser} from './target/dist/bundle.js';" + //
                "const parser = new Parser(); " + //
                "parser.parse(source);";

        Context cx = Context.newBuilder("js")
                .allowIO(true)
                .build();

        cx.getBindings("js").putMember("source", source);
        Value js = cx.eval(Source.newBuilder("js", script, "bundle.mjs").build());
        System.out.println(js);
    }

}
