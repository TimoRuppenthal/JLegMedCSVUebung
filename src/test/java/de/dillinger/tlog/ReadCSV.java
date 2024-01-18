package de.dillinger.tlog;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class ReadCSV {
    Map<String, String> AUTHOR_BOOK_MAP = new HashMap<>() {
        {
            put("Dan Simmons", "Hyperion");
            put("Douglas Adams", "The Hitchhiker's Guide to the Galaxy");
        }
    };
    String[] HEADERS = { "author", "title"};
@Test
void givenCSVFile_whenRead_thenContentsAsExpected() throws IOException {
    Reader in = new FileReader("src/test/java/de/dillinger/tlog/book.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
            .setHeader(HEADERS)
            .setSkipHeaderRecord(true)
            .build();

    Iterable<CSVRecord> records = csvFormat.parse(in);

    for (CSVRecord record : records) {
        String author = record.get("author");
        String title = record.get("title");
        assertEquals(AUTHOR_BOOK_MAP.get(author), title);
    }
}}