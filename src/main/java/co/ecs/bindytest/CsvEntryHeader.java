package co.ecs.bindytest;

import java.io.Serializable;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Section;

@Section(number = 1)
@CsvRecord(separator = ";", crlf = "UNIX", allowEmptyStream = true)
@SuppressWarnings("WeakerAccess")
public final class CsvEntryHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @DataField(pos = 1, position = 1, length = 2, required = true)
    private final RecTypeEnum recType = RecTypeEnum.HEADER;

    @DataField(pos = 2, position = 2, length = 3, required = true)
    private final String agencyNo;

    @DataField(pos = 3, position = 3, length = 5, required = true)
    private final String version;

    public CsvEntryHeader(final String agencyNo, final String version) {
        this.agencyNo = agencyNo;
        this.version = version;
    }

    @Override
    public String toString() {
        return "CsvEntryHeader{"
            + "recType=" + recType
            + ", agencyNo='" + agencyNo + '\''
            + ", version='" + version + '\''
            + '}';
    }
}
