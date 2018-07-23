package co.ecs.bindytest;

import java.io.Serializable;
import java.time.LocalTime;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Section;

@Section(number = 3)
@SuppressWarnings("WeakerAccess")
public final class CsvEntryFooter implements Serializable {

    private static final long serialVersionUID = 1L;

    @DataField(pos = 1, position = 1, length = 2, required = true)
    private final RecTypeEnum recType = RecTypeEnum.FOOTER;

    @DataField(pos = 2, position = 2, length = 5, required = true)
    private final String agencyNo;

    @DataField(pos = 3, position = 3, length = 6, required = true)
    private final LocalTime time;

    @DataField(pos = 4, position = 5, length = 1, required = true)
    private final Long counter;

    public CsvEntryFooter(final String agencyNo, final LocalTime time, final Long counter) {
        this.agencyNo = agencyNo;
        this.time = time;
        this.counter = counter;
    }
}
