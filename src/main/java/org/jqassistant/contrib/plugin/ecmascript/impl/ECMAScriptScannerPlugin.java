package org.jqassistant.contrib.plugin.ecmascript.impl;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.core.scanner.api.ScannerPlugin.Requires;
import com.buschmais.jqassistant.core.scanner.api.Scope;
import com.buschmais.jqassistant.plugin.common.api.model.FileDescriptor;
import com.buschmais.jqassistant.plugin.common.api.scanner.AbstractScannerPlugin;
import com.buschmais.jqassistant.plugin.common.api.scanner.filesystem.FileResource;
import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.jqassistant.contrib.plugin.ecmascript.api.model.ECMAScriptFileDescriptor;
import org.jqassistant.contrib.plugin.ecmascript.impl.graaljs.ECMAScriptParser;

import java.io.IOException;
import java.io.InputStream;

@Requires(FileDescriptor.class)
public class ECMAScriptScannerPlugin extends AbstractScannerPlugin<FileResource, ECMAScriptFileDescriptor> {

    private final ECMAScriptParser ecmaScriptParser = new ECMAScriptParser();

    @Override
    public boolean accepts(FileResource fileResource, String path, Scope scope) throws IOException {
        return path.endsWith(".js");
    }

    @Override
    public ECMAScriptFileDescriptor scan(FileResource fileResource, String path, Scope scope, Scanner scanner)
        throws IOException {
        FileDescriptor fileDescriptor = scanner.getContext()
            .getCurrentDescriptor();
        try (InputStream inputStream = fileResource.createStream()) {
            String source = IOUtils.toString(inputStream, Charsets.UTF_8);
            ecmaScriptParser.parse(source);
        }
        return scanner.getContext()
            .getStore()
            .addDescriptorType(fileDescriptor, ECMAScriptFileDescriptor.class);
    }
}
