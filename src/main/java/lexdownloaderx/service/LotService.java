package lexdownloaderx.service;

import javafx.concurrent.Task;
import net.caspervg.lex4j.bean.Lot;
import net.caspervg.lex4j.route.LotRoute;

/**
 * Service to get lot information
 */
public class LotService extends APIService<Lot> {

    private int id;

    @Override
    protected Task<Lot> createTask() {
        return new Task<Lot>() {
            protected Lot call() throws InterruptedException {
                LotRoute route = new LotRoute(getAuth());
                return route.getLot(id);
            }
        };
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
