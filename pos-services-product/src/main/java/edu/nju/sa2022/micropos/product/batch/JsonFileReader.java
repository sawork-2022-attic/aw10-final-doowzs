package edu.nju.sa2022.micropos.product.batch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Slf4j
public class JsonFileReader implements ResourceAwareItemReaderItemStream<JsonNode> {

    private Resource resource;
    private BufferedReader bufferedReader;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public JsonNode read() throws Exception {
        if (bufferedReader == null) {
            try {
                File file = resource.getFile();
                FileReader fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
            } catch (Exception e) {
                log.error("Failed to read file", e);
                return null;
            }
        }

        String line = bufferedReader.readLine();
        if (line == null) {
            return null;
        }
        return objectMapper.readTree(line);
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
    }

    @Override
    public void close() throws ItemStreamException {
    }

}
