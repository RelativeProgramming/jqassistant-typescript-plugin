package org.jqassistant.contrib.plugin.typescript.api.model;

import com.buschmais.jqassistant.plugin.common.api.model.FileDescriptor;
import com.buschmais.xo.neo4j.api.annotation.Label;

@Label
public interface TypeScriptFileDescriptor extends FileDescriptor, TypeScriptDescriptor {
}
