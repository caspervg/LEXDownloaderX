package lexdownloaderx.cleanitol;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CleanitolParser {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static Future<List<CleanitolItem>> parseCleanitol(final Path path) {

        return executorService.submit(new Callable<List<CleanitolItem>>() {
            @Override
            public List<CleanitolItem> call() throws Exception {
                List<String> entries = Files.readAllLines(path, StandardCharsets.UTF_8);
                List<CleanitolItem> items = new ArrayList<>();

                for (String entry : entries) {
                    if (entry.length() < 1) continue;   // Empty line

                    String[] split = entry.split(";");
                    if (split.length < 2) continue;     // Bad or RemoveList line

                    CleanitolItem item;
                    if (! split[1].contains("csxlex")) {
                        // Not a LEX file
                        item = new CleanitolItemBuilder()
                                .fileName(split[0])
                                .id(-1)
                                .Url(new URL(split[1]))
                                .build();
                    } else {
                        // A LEX file
                        int lotid = Integer.parseInt(split[1].substring(split[1].lastIndexOf('=')+1));

                        item = new CleanitolItemBuilder()
                                .fileName(split[0])
                                .id(lotid)
                                .Url(new URL(split[1]))
                                .build();
                    }

                    items.add(item);
                }

                return items;
            }
        });

    }
}
