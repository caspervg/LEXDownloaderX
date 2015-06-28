package lexdownloaderx.cleanitol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Future;

public class CleanitolParserTest {

    private Path filePath;

    @Before
    public void setUp() throws Exception {
        filePath = FileSystems.getDefault().getPath(getClass().getClassLoader().getResource("DependencyCollector_AalesundComplex.txt").getPath());
    }

    @Test
    public void testParseCleanitol() throws Exception {
        Future<List<CleanitolItem>> fut = CleanitolParser.parseCleanitol(filePath);
        List<CleanitolItem> items = fut.get();

        Assert.assertNotNull(items);
        Assert.assertEquals(19, items.size());


        Assert.assertEquals("Tromsdalen Church-0x5ad0e817_0xf33fb8ac_0x280000.SC4Model",
                items.get(0).getFileName());

        Assert.assertEquals(-1,
                items.get(0).getId());

        Assert.assertEquals(new URL("http://www.simtropolis.com/stex/details.cfm?id=18221"),
                items.get(0).getUrl());


        Assert.assertEquals("CAS Essentials.dat",
                items.get(items.size()-1).getFileName());

        Assert.assertEquals(2324,
                items.get(items.size()-1).getId());

        Assert.assertEquals(new URL("http://sc4devotion.com/csxlex/lex_filedesc.php?lotGET=2324"),
                items.get(items.size()-1).getUrl());
    }
}