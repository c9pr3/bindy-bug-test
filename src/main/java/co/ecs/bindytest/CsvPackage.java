package co.ecs.bindytest;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.Link;
import org.apache.camel.dataformat.bindy.annotation.LinkType;
import org.apache.camel.dataformat.bindy.annotation.OneToMany;

@CsvRecord(separator = ";", crlf = "UNIX", allowEmptyStream = true)
@SuppressWarnings("WeakerAccess")
public final class CsvPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Link(linkType = LinkType.OneToOne)
    private final CsvEntryHeader header;

    @Link
    @OneToMany
    private final List<CsvEntryBody> entries = new LinkedList<>();

    @Link(linkType = LinkType.OneToOne)
    private final CsvEntryFooter footer;

    public CsvPackage(final CsvEntryHeader header, final CsvEntryFooter footer) {
        this.header = header;
        this.footer = footer;
    }

    public List<CsvEntryBody> getEntries() {
        return entries;
    }
}
