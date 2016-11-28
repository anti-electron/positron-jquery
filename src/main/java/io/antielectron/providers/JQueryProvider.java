package io.antielectron.providers;

import io.antielectron.framework.app.IDependencyProvider;
import io.antielectron.framework.js.JSGlobals;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * Provides JQuery for use in a Positron app.
 */
public class JQueryProvider implements IDependencyProvider {

    @Override
    public void injectJs(JSGlobals globals, Logger log) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(JQueryProvider.class.getClassLoader().getResourceAsStream("jquery.min.js")))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            globals.putExecution(sb.toString());
        } catch (IOException e) {
            log.warn("Failed to load JQuery!", e);
        }
    }

    @Override
    public void injectCss(Writer css, Logger log) {
        // NO-OP
    }

}
