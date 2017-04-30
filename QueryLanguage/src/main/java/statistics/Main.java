package statistics;

import java.util.ArrayList;
import java.util.List;
import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        List<Matcher> ml = new ArrayList<>();
        QueryBuilder query = new QueryBuilder();

        ml.add(query.hasAtLeast(10, "goals")
                .hasFewerThan(14, "goals")
                .playsIn("PHI")
                .hasAtLeast(10, "assists")
                .build());

        ml.add(query.oneOf(
                query.playsIn("NYR").playsIn("NSH").build(),
                query.playsIn("NYR").playsIn("TBL").build()
        ).build());

        ml.add(query.oneOf(
                query.playsIn("NYI").build(),
                query.playsIn("WPG").build()
        ).hasAtLeast(10, "goals").build());

        for (Matcher matcher : ml) {
            for (Player player : stats.matches(matcher)) {
                System.out.println(player);
            }
        }
    }
}
