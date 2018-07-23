package co.ecs.bindytest;

import java.io.ByteArrayOutputStream;
import java.time.LocalTime;
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
    private static final BindyCsvDataFormat bindy = new BindyCsvDataFormat(CsvPackage.class);

    @Test
    public void testCsvPackageOutput() throws Exception {
        String out;

        System.out.print("Checking header and footer...should be OK...");
        final CsvEntryHeader header = new CsvEntryHeader("agency1", "00.01");
        final CsvEntryFooter footer = new CsvEntryFooter("agency1", LocalTime.now(), 1L);
        final CsvPackage csvPackage = new CsvPackage(header, footer);
        final ByteArrayOutputStream stringArrayOutputStream = new ByteArrayOutputStream();
        bindy.marshal(new DefaultExchange(new DefaultCamelContext()), csvPackage, stringArrayOutputStream);
        out = stringArrayOutputStream.toString();
        Assert.assertEquals("HEADER;agency1;00.01;1\nFOOTER;agency1;;1\n", out);
        System.out.println("DONE");

        System.out.print("Checking with one entry...will NOT be OK...");
        final CsvEntryBody csvEntryBody = new CsvEntryBody();
        csvPackage.getEntries().add(csvEntryBody);

        stringArrayOutputStream.reset();
        bindy.marshal(new DefaultExchange(new DefaultCamelContext()), csvPackage, stringArrayOutputStream);
        out = stringArrayOutputStream.toString();
        Assert.assertEquals("HEADER;agency1;00.01;1\nBODY\nFOOTER;agency1;;1\n", out);
        System.out.println("DONE");
    }

}