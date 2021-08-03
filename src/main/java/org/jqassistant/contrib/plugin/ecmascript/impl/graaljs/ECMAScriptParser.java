package org.jqassistant.contrib.plugin.ecmascript.impl.graaljs;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

@Slf4j
public class ECMAScriptParser {

    private static final String LANGUAGE_ID = "js";
    private static final String ENGINE_WARN_INTERPRETER_ONLY = "engine.WarnInterpreterOnly";

    private static final ClasspathFilesystem CLASSPATH_FILESYSTEM = new ClasspathFilesystem();

    public void parse(String source) throws IOException {
        String parserScript =
            "import {Parser} from 'classpath:ecmascript-parser-bundle.js'; new Parser().parse(source,ecmaVersion,sourceType)";
        Source parserSource = Source.newBuilder(LANGUAGE_ID, parserScript, "parser.mjs")
            .build();

        try (Context cx = Context.newBuilder(LANGUAGE_ID)
            .allowIO(true)
            .fileSystem(CLASSPATH_FILESYSTEM)
            .option(ENGINE_WARN_INTERPRETER_ONLY, "false")
            .build()) {
            Value bindings = cx.getBindings(LANGUAGE_ID);
            bindings.putMember("source", source);
            bindings.putMember("ecmaVersion", "latest");
            bindings.putMember("sourceType", "module");
            Value ast = cx.eval(parserSource);
            log.info("Parse result: {}", ast);
        }
    }

}
