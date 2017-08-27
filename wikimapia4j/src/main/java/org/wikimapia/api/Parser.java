package org.wikimapia.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.InputStream;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class Parser {

    public Categories parseCategoryResults(String is) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Categories ret = mapper.readValue( is, Categories.class);

        return ret;
    }

    public Categories parseCategoryResults(InputStream is) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Categories ret = mapper.readValue( is, Categories.class);

        return ret;
    }

    public SearchResults parseResults(InputStream is ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        SearchResults ret = mapper.readValue( is, SearchResults.class);

        return ret;
    }

    public SearchResults parseResults(String is ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, new SearchResults());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        SearchResults ret = mapper.readValue( is, SearchResults.class);

        return ret;
    }

}
