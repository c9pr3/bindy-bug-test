package co.ecs.bindytest;

import java.io.Serializable;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Section;

@Section(number = 2)
@SuppressWarnings("WeakerAccess")
public final class CsvEntryBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @DataField(pos = 1, position = 1, required = true)
    private final RecTypeEnum recType = RecTypeEnum.BODY;

}
