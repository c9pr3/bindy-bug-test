package co.ecs.bindytest;

import java.io.ByteArrayOutputStream;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Assert;
import org.junit.Test;

/**
 * CsvPackageTest.
 *
 * @author Christian Scharmach (cs@2scale.net)
 * @since 23.07.18
 */
public final class CsvPackageTest {

    @Test
    public void testCsvPackageOutputDoesWorkWithHeaderAndFooterOnly() throws Exception {
        final BindyCsvDataFormat bindy = new BindyCsvDataFormat(CsvPackage.class);
        final ByteArrayOutputStream stringArrayOutputStream = new ByteArrayOutputStream();

        final CsvPackage csvPackage = createCsvPackage(Collections.emptyList());
        bindy.marshal(new DefaultExchange(new DefaultCamelContext()), csvPackage, stringArrayOutputStream);

        final String out = stringArrayOutputStream.toString();
        Assert.assertEquals("HEADER;agency1;00.01;1\nFOOTER;agency1;;1\n", out);
    }

    @Test
    public void testCsvPackageDoesNOTWorkWithBody() throws Exception {
        final BindyCsvDataFormat bindy = new BindyCsvDataFormat(CsvPackage.class);
        final ByteArrayOutputStream stringArrayOutputStream = new ByteArrayOutputStream();

        final CsvPackage csvPackage = createCsvPackage(Collections.singletonList(new CsvEntryBody()));
        bindy.marshal(new DefaultExchange(new DefaultCamelContext()), csvPackage, stringArrayOutputStream);

        final String out = stringArrayOutputStream.toString();
        Assert.assertEquals("HEADER;agency1;00.01;1\nBODY\nFOOTER;agency1;;1\n", out);
    }

    private static CsvPackage createCsvPackage(final List<CsvEntryBody> csvEntryBodyList) {
        final CsvEntryHeader header = new CsvEntryHeader("agency1", "00.01");
        final CsvEntryFooter footer = new CsvEntryFooter("agency1", LocalTime.now(), 1L);
        final CsvPackage csvPackage = new CsvPackage(header, footer);
        csvPackage.getEntries().addAll(csvEntryBodyList);
        return csvPackage;
    }

}