package org.wikimapia.api;


import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * created on 1/11/2017.
 *
 * @author Alex O'Ree
 */

public class ParserTest {

    final String doc="{\n" +
        "\t\"language\": \"en\",\n" +
        "\t\"found\": \"3\",\n" +
        "\t\"places\": [{\n" +
        "\t\t\"id\": 1691851,\n" +
        "\t\t\"language_id\": 0,\n" +
        "\t\t\"language_iso\": \"en\",\n" +
        "\t\t\"urlhtml\": \"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org\\/1691851\\/Creperie\\\">Creperie<\\/a>\",\n" +
        "\t\t\"title\": \"Creperie\",\n" +
        "\t\t\"tags\": [{\n" +
        "\t\t\t\"id\": 74,\n" +
        "\t\t\t\"title\": \"restaurant\"\n" +
        "\t\t}, {\n" +
        "\t\t\t\"id\": 8830,\n" +
        "\t\t\t\"title\": \"pancakes\"\n" +
        "\t\t}],\n" +
        "\t\t\"location\": {\n" +
        "\t\t\t\"lon\": 2.2926627,\n" +
        "\t\t\t\"lat\": 48.8590494,\n" +
        "\t\t\t\"north\": 48.8590942,\n" +
        "\t\t\t\"south\": 48.8590046,\n" +
        "\t\t\t\"east\": 2.2927362,\n" +
        "\t\t\t\"west\": 2.2925893,\n" +
        "\t\t\t\"country\": \"France\",\n" +
        "\t\t\t\"state\": \"Ile-de-France\",\n" +
        "\t\t\t\"place\": \"Vanves\",\n" +
        "\t\t\t\"country_adm_id\": 101056,\n" +
        "\t\t\t\"gadm\": [{\n" +
        "\t\t\t\t\"id\": \"74\",\n" +
        "\t\t\t\t\"country\": \"74\",\n" +
        "\t\t\t\t\"level\": \"0\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"France\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"France\"\n" +
        "\t\t\t}, {\n" +
        "\t\t\t\t\"id\": \"1000\",\n" +
        "\t\t\t\t\"country\": \"0\",\n" +
        "\t\t\t\t\"level\": \"0\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"World\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"World\"\n" +
        "\t\t\t}, {\n" +
        "\t\t\t\t\"id\": \"60533\",\n" +
        "\t\t\t\t\"country\": \"74\",\n" +
        "\t\t\t\t\"level\": \"1\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"\\u00cele-de-France\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"\\u00cele-de-France\"\n" +
        "\t\t\t}, {\n" +
        "\t\t\t\t\"id\": \"83881\",\n" +
        "\t\t\t\t\"country\": \"74\",\n" +
        "\t\t\t\t\"level\": \"2\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"Paris\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"Paris\"\n" +
        "\t\t\t}, {\n" +
        "\t\t\t\t\"id\": \"101054\",\n" +
        "\t\t\t\t\"country\": \"74\",\n" +
        "\t\t\t\t\"level\": \"3\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"Paris, 7e arrondissement\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"Paris, 7e arrondissement\"\n" +
        "\t\t\t}, {\n" +
        "\t\t\t\t\"id\": \"101055\",\n" +
        "\t\t\t\t\"country\": \"74\",\n" +
        "\t\t\t\t\"level\": \"4\",\n" +
        "\t\t\t\t\"is_last_level\": \"0\",\n" +
        "\t\t\t\t\"name\": \"Paris, 7e arrondissement\",\n" +
        "\t\t\t\t\"iso\": null,\n" +
        "\t\t\t\t\"type\": null,\n" +
        "\t\t\t\t\"translation\": \"Paris, 7e arrondissement\"\n" +
        "\t\t\t}],\n" +
        "\t\t\t\"city_id\": \"5009811\",\n" +
        "\t\t\t\"city\": \"Paris\",\n" +
        "\t\t\t\"zoom\": 20\n" +
        "\t\t},\n" +
        "\t\t\"polygon\": [{\n" +
        "\t\t\t\"x\": 2.2926336,\n" +
        "\t\t\t\"y\": 48.8590046\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926544,\n" +
        "\t\t\t\"y\": 48.8590046\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927147,\n" +
        "\t\t\t\"y\": 48.8590479\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927154,\n" +
        "\t\t\t\"y\": 48.8590554\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927288,\n" +
        "\t\t\t\"y\": 48.8590686\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927362,\n" +
        "\t\t\t\"y\": 48.8590906\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927194,\n" +
        "\t\t\t\"y\": 48.8590942\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927127,\n" +
        "\t\t\t\"y\": 48.859077\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.29271,\n" +
        "\t\t\t\"y\": 48.8590792\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927053,\n" +
        "\t\t\t\"y\": 48.8590765\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926959,\n" +
        "\t\t\t\"y\": 48.8590823\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926825,\n" +
        "\t\t\t\"y\": 48.8590735\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926691,\n" +
        "\t\t\t\"y\": 48.8590717\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926383,\n" +
        "\t\t\t\"y\": 48.8590509\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926228,\n" +
        "\t\t\t\"y\": 48.8590589\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2925893,\n" +
        "\t\t\t\"y\": 48.8590346\n" +
        "\t\t}]\n" +
        "\t}, {\n" +
        "\t\t\"id\": 26144807,\n" +
        "\t\t\"language_id\": 0,\n" +
        "\t\t\"language_iso\": \"en\",\n" +
        "\t\t\"urlhtml\": \"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org\\/26144807\\/Roman-warrior\\\">Roman warrior<\\/a>\",\n" +
        "\t\t\"title\": \"Roman warrior\",\n" +
        "\t\t\"tags\": [{\n" +
        "\t\t\t\"id\": 59267,\n" +
        "\t\t\t\"title\": \"equestrian statue\"\n" +
        "\t\t}],\n" +
        "\t\t\"location\": {\n" +
        "\t\t\t\"lon\": 2.292689,\n" +
        "\t\t\t\"lat\": 48.8592042,\n" +
        "\t\t\t\"north\": 48.8592289,\n" +
        "\t\t\t\"south\": 48.8591795,\n" +
        "\t\t\t\"east\": 2.2927257,\n" +
        "\t\t\t\"west\": 2.2926523,\n" +
        "\t\t\t\"country\": \"France\",\n" +
        "\t\t\t\"state\": \"Ile-de-France\",\n" +
        "\t\t\t\"place\": \"Vanves\",\n" +
        "\t\t\t\"country_adm_id\": 0,\n" +
        "\t\t\t\"city_id\": \"5009811\",\n" +
        "\t\t\t\"city\": \"Paris\",\n" +
        "\t\t\t\"zoom\": 21\n" +
        "\t\t},\n" +
        "\t\t\"polygon\": [{\n" +
        "\t\t\t\"x\": 2.2926523,\n" +
        "\t\t\t\"y\": 48.8591978\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926958,\n" +
        "\t\t\t\"y\": 48.8592289\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2927257,\n" +
        "\t\t\t\"y\": 48.8592103\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2926821,\n" +
        "\t\t\t\"y\": 48.8591795\n" +
        "\t\t}]\n" +
        "\t}, {\n" +
        "\t\t\"id\": 26144812,\n" +
        "\t\t\"language_id\": 0,\n" +
        "\t\t\"language_iso\": \"en\",\n" +
        "\t\t\"urlhtml\": \"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org\\/26144812\\/Gallic-warrior\\\">Gallic warrior<\\/a>\",\n" +
        "\t\t\"title\": \"Gallic warrior\",\n" +
        "\t\t\"tags\": [{\n" +
        "\t\t\t\"id\": 59267,\n" +
        "\t\t\t\"title\": \"equestrian statue\"\n" +
        "\t\t}],\n" +
        "\t\t\"location\": {\n" +
        "\t\t\t\"lon\": 2.2930298,\n" +
        "\t\t\t\"lat\": 48.8594448,\n" +
        "\t\t\t\"north\": 48.8594687,\n" +
        "\t\t\t\"south\": 48.8594209,\n" +
        "\t\t\t\"east\": 2.2930661,\n" +
        "\t\t\t\"west\": 2.2929936,\n" +
        "\t\t\t\"country\": \"France\",\n" +
        "\t\t\t\"state\": \"Ile-de-France\",\n" +
        "\t\t\t\"place\": \"Vanves\",\n" +
        "\t\t\t\"country_adm_id\": 0,\n" +
        "\t\t\t\"city_id\": \"5009811\",\n" +
        "\t\t\t\"city\": \"Paris\",\n" +
        "\t\t\t\"zoom\": 21\n" +
        "\t\t},\n" +
        "\t\t\"polygon\": [{\n" +
        "\t\t\t\"x\": 2.2929936,\n" +
        "\t\t\t\"y\": 48.8594404\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2930355,\n" +
        "\t\t\t\"y\": 48.8594687\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2930661,\n" +
        "\t\t\t\"y\": 48.859449\n" +
        "\t\t}, {\n" +
        "\t\t\t\"x\": 2.2930236,\n" +
        "\t\t\t\"y\": 48.8594209\n" +
        "\t\t}]\n" +
        "\t}],\n" +
        "\t\"page\": 1,\n" +
        "\t\"count\": 50\n" +
        "}";
    @Test
    public void runTest() throws Exception {
        Parser p = new Parser();
      //  ByteArrayInputStream is = new ByteArrayInputStream(doc.getBytes());
        SearchResults searchResults = p.parseResults(doc);
        Assert.assertNotNull(searchResults);
    }

    final String categories="{\n" +
        "\t\"categories\": [{\n" +
        "\t\t\"id\": 46535,\n" +
        "\t\t\"amount\": 27745617,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place without photos\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46534,\n" +
        "\t\t\"amount\": 18086888,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place without description\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46532,\n" +
        "\t\t\"amount\": 14720605,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place without category\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46533,\n" +
        "\t\t\"amount\": 7303635,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"building without address\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46520,\n" +
        "\t\t\"amount\": 3298018,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place without polygon\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 109,\n" +
        "\t\t\"amount\": 2186401,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"building\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 164,\n" +
        "\t\t\"amount\": 2179571,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/45.png\",\n" +
        "\t\t\"name\": \"house\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 164,\n" +
        "\t\t\"amount\": 2179571,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/45.png\",\n" +
        "\t\t\"name\": \"home\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 164,\n" +
        "\t\t\"amount\": 2179571,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/45.png\",\n" +
        "\t\t\"name\": \"dwelling\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 164,\n" +
        "\t\t\"amount\": 2179571,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/45.png\",\n" +
        "\t\t\"name\": \"residence\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 164,\n" +
        "\t\t\"amount\": 2179571,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/45.png\",\n" +
        "\t\t\"name\": \"villa\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44865,\n" +
        "\t\t\"amount\": 1347051,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"apartments\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44865,\n" +
        "\t\t\"amount\": 1347051,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"tenement\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44865,\n" +
        "\t\t\"amount\": 1347051,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"tower block\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44865,\n" +
        "\t\t\"amount\": 1347051,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"apartment building\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44865,\n" +
        "\t\t\"amount\": 1347051,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"block of flats\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46531,\n" +
        "\t\t\"amount\": 1305651,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place with triangular polygon\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 949,\n" +
        "\t\t\"amount\": 1169547,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/57.png\",\n" +
        "\t\t\"name\": \"village\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 7,\n" +
        "\t\t\"amount\": 1143454,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"shopping and services\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 17,\n" +
        "\t\t\"amount\": 1133835,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/97.png\",\n" +
        "\t\t\"name\": \"store \\/ shop\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 203,\n" +
        "\t\t\"amount\": 884623,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/50.png\",\n" +
        "\t\t\"name\": \"school\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 203,\n" +
        "\t\t\"amount\": 884623,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/50.png\",\n" +
        "\t\t\"name\": \"education\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 203,\n" +
        "\t\t\"amount\": 884623,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/50.png\",\n" +
        "\t\t\"name\": \"schoolhouse\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 203,\n" +
        "\t\t\"amount\": 884623,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/50.png\",\n" +
        "\t\t\"name\": \"schooling\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 4,\n" +
        "\t\t\"amount\": 857353,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"dining and leisure\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 1663,\n" +
        "\t\t\"amount\": 798144,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"faith\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 1663,\n" +
        "\t\t\"amount\": 798144,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"religion\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 1663,\n" +
        "\t\t\"amount\": 798144,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"religious\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 45693,\n" +
        "\t\t\"amount\": 678728,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"place of worship\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 391,\n" +
        "\t\t\"amount\": 662613,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"water\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 45716,\n" +
        "\t\t\"amount\": 585748,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"nonresidential building\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 74,\n" +
        "\t\t\"amount\": 520639,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/47.png\",\n" +
        "\t\t\"name\": \"restaurant\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 74,\n" +
        "\t\t\"amount\": 520639,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/47.png\",\n" +
        "\t\t\"name\": \"eatery\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 332,\n" +
        "\t\t\"amount\": 483913,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/44.png\",\n" +
        "\t\t\"name\": \"lake\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 48368,\n" +
        "\t\t\"amount\": 476183,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"administrative division\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 4979,\n" +
        "\t\t\"amount\": 429696,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/06.png\",\n" +
        "\t\t\"name\": \"production\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 4979,\n" +
        "\t\t\"amount\": 429696,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/06.png\",\n" +
        "\t\t\"name\": \"industry\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 5803,\n" +
        "\t\t\"amount\": 389761,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"venues\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 163,\n" +
        "\t\t\"amount\": 389654,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"sports center\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 163,\n" +
        "\t\t\"amount\": 389654,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"sports venue\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 163,\n" +
        "\t\t\"amount\": 389654,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"sports centre\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 194,\n" +
        "\t\t\"amount\": 389626,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/14.png\",\n" +
        "\t\t\"name\": \"park\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44603,\n" +
        "\t\t\"amount\": 373521,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"electric power\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 651,\n" +
        "\t\t\"amount\": 358336,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"christianity\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 1275,\n" +
        "\t\t\"amount\": 343265,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"medical\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 46591,\n" +
        "\t\t\"amount\": 331521,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/00.png\",\n" +
        "\t\t\"name\": \"do not draw title\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 50,\n" +
        "\t\t\"amount\": 308395,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/01\\/04.png\",\n" +
        "\t\t\"name\": \"hotel\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44607,\n" +
        "\t\t\"amount\": 306252,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/43.png\",\n" +
        "\t\t\"name\": \"bridge\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 44607,\n" +
        "\t\t\"amount\": 306252,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/43.png\",\n" +
        "\t\t\"name\": \"pont\"\n" +
        "\t}, {\n" +
        "\t\t\"id\": 122,\n" +
        "\t\t\"amount\": 289971,\n" +
        "\t\t\"icon\": \"http:\\/\\/wikimapia.org\\/mapico\\/00\\/00\\/00\\/00\\/61.png\",\n" +
        "\t\t\"name\": \"church\"\n" +
        "\t}],\n" +
        "\t\"found\": 6528,\n" +
        "\t\"page\": 1,\n" +
        "\t\"count\": 50\n" +
        "}";

    @Test
    public void categories() throws Exception{
        Parser p = new Parser();
        Categories categories = p.parseCategoryResults(this.categories);
        Assert.assertNotNull(categories);
    }
}
