package org.jqassistant.contrib.plugin.typescript.impl.graaljs;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

@Slf4j
public class TypeScriptParser {

    private static final String LANGUAGE_ID = "js";
    private static final String ENGINE_WARN_INTERPRETER_ONLY = "engine.WarnInterpreterOnly";

    private static final ClasspathFilesystem CLASSPATH_FILESYSTEM = new ClasspathFilesystem();

    public void parse(String source, String sourceFilePath) throws IOException {
        String parserScript =
            "import {Parser} from 'classpath:parser-bundle.js'; new Parser().parse(source, sourceFilePath)";
        Source parserSource = Source.newBuilder(LANGUAGE_ID, parserScript, "parser.mjs")
            .build();

        try (Context cx = Context.newBuilder(LANGUAGE_ID)
            .allowIO(true)
            .fileSystem(CLASSPATH_FILESYSTEM)
            .option(ENGINE_WARN_INTERPRETER_ONLY, "false")
            .build()) {
            Value bindings = cx.getBindings(LANGUAGE_ID);
            bindings.putMember("source", source);
            bindings.putMember("sourceFilePath", sourceFilePath);
            Value res = cx.eval(parserSource);
            log.info("Parse result: {}", res);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
