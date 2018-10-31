package printer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    Printer printer;
    @BeforeEach
    void setup(){
        printer = new Printer();
    }

    @Test
    void givenStoppedPrinterWhenAskingForInvalidFunctionExpectException() {
        printer.stop();

        assertThrows(Exception.class,()->printer.print("",""));
        assertThrows(Exception.class,()->printer.queue());
        assertThrows(Exception.class,()->printer.topQueue(0));
        assertThrows(Exception.class,()->printer.readConfig(""));
        assertThrows(Exception.class,()->printer.setConfig("",""));
    }

    @Test
    void givenStoppedPrinterThenStatusIsStopped(){
        printer.stop();
        assertTrue(printer.status().equals("Stopped"));
    }

    @Test
    void givenStoppedPrinterWhenStartingPrinterThenStatusIsStarted(){
        printer.stop();
        printer.start();
        assertTrue(printer.status().equals("Started"));
    }
    @Test
    void givenDefaultPrinterThenStatusIsStarted(){
        assertTrue(printer.status().equals("Started"));
    }
    @Test
    void givenDefaultPrinterWithPrintQueueThenQueueIsCorrect(){
        List<String> fileNames= Arrays.asList("a","b","c","d","e");
        fileNames.stream().forEach(i-> printer.print(i,""));
        assertEquals(fileNames,printer.queue().stream().map(i->i.fileName).collect(Collectors.toList()));
    }

    @Test
    void givenDefaultPrinterThenSetReadConfigWorks(){
        String value="hello";
        String param="param1";
        printer.setConfig(param,value);
        assertEquals(value, printer.readConfig(param));
    }

    @Test
    void givenDefaultPrinterWithPrintQueueWhenRestartingPrintQueueEmpty(){
        List<String> fileNames= Arrays.asList("a","b","c","d","e");
        fileNames.stream().forEach(i-> printer.print(i,""));
        printer.restart();
        assertTrue(printer.queue().isEmpty());
    }

    @Test
    void givenDefaultPrinterWithPrintQueueWhenMovingElementToFrontOfQueueThenElementIsInFrontOfQueue(){
        List<String> fileNames= Arrays.asList("a","b","c","d","e");
        fileNames.stream().forEach(i-> printer.print(i,""));
        Job job= printer.queue().get(3);
        printer.topQueue(job.jobNumber);
        assertEquals(printer.queue().get(0),job);
    }

}