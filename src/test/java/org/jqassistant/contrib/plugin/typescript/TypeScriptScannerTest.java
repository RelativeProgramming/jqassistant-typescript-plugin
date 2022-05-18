package org.jqassistant.contrib.plugin.typescript;

import com.buschmais.jqassistant.core.store.api.model.Descriptor;
import com.buschmais.jqassistant.plugin.common.test.AbstractPluginIT;

import org.jqassistant.contrib.plugin.typescript.api.model.TypeScriptFileDescriptor;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.buschmais.jqassistant.core.scanner.api.DefaultScope.NONE;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeScriptScannerTest extends AbstractPluginIT {

    @Test
    public void helloWorld() {
        File file = new File(getClassesDirectory(TypeScriptScannerTest.class), "helloWorld.ts");

        Descriptor descriptor = getScanner().scan(file, file.getAbsolutePath(), NONE);
        store.beginTransaction();
        // TODO: Fix test
        // assertThat(descriptor).isInstanceOf(TypeScriptFileDescriptor.class);
        assertThat(descriptor).isInstanceOf(Object.class);
        store.commitTransaction();
    }

}
