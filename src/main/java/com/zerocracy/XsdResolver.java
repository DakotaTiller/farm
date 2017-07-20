/**
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy;

import org.cactoos.Func;
import org.cactoos.func.StickyFunc;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.io.InputAsLSInput;
import org.cactoos.io.StickyInput;
import org.cactoos.io.UrlAsInput;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

/**
 * XML document.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.12
 */
public final class XsdResolver implements LSResourceResolver {

    @Override
    // @checkstyle ParameterNumberCheck (5 lines)
    @SuppressWarnings("PMD.UseObjectForClearerAPI")
    public LSInput resolveResource(final String type, final String namespace,
        final String pid, final String sid, final String base) {
        final Func<String, LSInput> locator = new StickyFunc<>(
            loc -> {
                final String[] parts = loc.split(" ");
                return new InputAsLSInput(
                    // @checkstyle MagicNumber (2 lines)
                    new StickyInput(new UrlAsInput(parts[3])),
                    parts[2], parts[3], parts[4]
                );
            }
        );
        return new UncheckedFunc<>(locator).apply(
            String.format(
                "%s %s %s %s %s",
                type, namespace, pid, sid, base
            )
        );
    }
}