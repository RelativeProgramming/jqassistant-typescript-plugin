package org.jqassistant.contrib.plugin.typescript.impl;

import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.core.scanner.api.ScannerPlugin.Requires;
import com.buschmais.jqassistant.core.scanner.api.Scope;
import com.buschmais.jqassistant.plugin.common.api.model.FileDescriptor;
import com.buschmais.jqassistant.plugin.common.api.scanner.AbstractScannerPlugin;
import com.buschmais.jqassistant.plugin.common.api.scanner.filesystem.FileResource;
import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.jqassistant.contrib.plugin.typescript.api.model.TypeScriptFileDescriptor;
import org.jqassistant.contrib.plugin.typescript.impl.graaljs.TypeScriptParser;

import java.io.IOException;
import java.io.InputStream;

@Requires(FileDescriptor.class)
public class TypeScriptScannerPlugin extends AbstractScannerPlugin<FileResource, TypeScriptFileDescriptor> {

    private final TypeScriptParser typeScriptParser = new TypeScriptParser();

    @Override
    public boolean accepts(FileResource fileResource, String path, Scope scope) throws IOException {
        return path.endsWith(".js");
    }

    @Override
    public TypeScriptFileDescriptor scan(FileResource fileResource, String path, Scope scope, Scanner scanner)
        throws IOException {
        FileDescriptor fileDescriptor = scanner.getContext()
            .getCurrentDescriptor();
        try (InputStream inputStream = fileResource.createStream()) {
            String source = IOUtils.toString(inputStream, Charsets.UTF_8);
            typeScriptParser.parse(source, fileResource.getFile().getAbsolutePath());
        }
        return scanner.getContext()
            .getStore()
            .addDescriptorType(fileDescriptor, TypeScriptFileDescriptor.class);
    }
}
