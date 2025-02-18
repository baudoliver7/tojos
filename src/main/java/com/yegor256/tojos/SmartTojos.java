/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2021 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.yegor256.tojos;

import java.util.List;
import java.util.function.Function;

/**
 * All file-objects.
 *
 * The class is NOT thread-safe.
 *
 * @since 0.3.0
 */
public final class SmartTojos implements Tojos {

    /**
     * The original.
     */
    private final Tojos origin;

    /**
     * Ctor.
     *
     * @param tojos The origin
     */
    public SmartTojos(final Tojos tojos) {
        this.origin = tojos;
    }

    /**
     * Get one tojo by ID.
     * @param name The id
     * @return The tojo if found
     */
    public Tojo getById(final String name) {
        return this.origin
            .select(tojo -> name.equals(tojo.get("id")))
            .iterator()
            .next();
    }

    /**
     * Get size.
     * @return Total count
     */
    public int size() {
        return this.origin.select(t -> true).size();
    }

    @Override
    public Tojo add(final String name) {
        return this.origin.add(name);
    }

    @Override
    public List<Tojo> select(final Function<Tojo, Boolean> filter) {
        return this.origin.select(filter);
    }
}
