package org.jqassistant.contrib.plugin.typescript;

import com.buschmais.jqassistant.core.store.api.model.Descriptor;
import com.buschmais.jqassistant.plugin.common.test.AbstractPluginIT;
import org.jqassistant.contrib.plugin.ecmascript.api.model.ECMAScriptFileDescriptor;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.buschmais.jqassistant.core.scanner.api.DefaultScope.NONE;
import static org.assertj.core.api.Assertions.assertThat;

public class ECMAScriptScannerTest extends AbstractPluginIT {

    @Test
    public void helloWorld() {
        File file = new File(getClassesDirectory(ECMAScriptScannerTest.class), "helloWorld.js");

        Descriptor descriptor = getScanner().scan(file, file.getAbsolutePath(), NONE);

        assertThat(descriptor).isInstanceOf(ECMAScriptFileDescriptor.class);
    }

}
