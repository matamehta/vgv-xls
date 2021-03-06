/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vgv.xls;

import com.jcabi.immutable.Array;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * XsRow.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XsRow implements ERow {

    /**
     * Array of cells.
     */
    private final Array<ECell> cells;

    /**
     * Ctor.
     * @param elements Cells
     */
    public XsRow(final ECell... elements) {
        this(new Array<>(elements));
    }

    /**
     * Ctor.
     * @param elements Cells
     */
    public XsRow(final Iterable<ECell> elements) {
        this.cells = new Array<>(elements);
    }

    @Override
    public Row attachTo(final Sheet sheet) {
        final Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        for (final ECell cell : this.cells) {
            cell.attachTo(row);
        }
        return row;
    }

    @Override
    public ERow with(final Style style) {
        final List<ECell> elements = new ArrayList<>(this.cells.size());
        for (final ECell cell : this.cells) {
            elements.add(cell.with(style));
        }
        return new XsRow(elements);
    }

    @Override
    public ERow with(final ECell... elements) {
        return new XsRow(this.cells.with(new Array<>(elements)));
    }

    @Override
    public ERow with(final ECell cell) {
        return new XsRow(this.cells.with(cell));
    }

    @Override
    public ERow with(final ECells elements) {
        return new XsRow(this.cells.with(elements.asArray()));
    }
}
