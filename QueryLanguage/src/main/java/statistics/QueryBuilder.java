package statistics;

import java.util.ArrayList;
import java.util.List;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {

    Matcher matcher;
    List<Matcher> matchers;

    public QueryBuilder() {
        this.matchers = new ArrayList<>();
    }

    public Matcher build() {
        Matcher and = new And(matchers.toArray(new Matcher[0]));
        this.matchers = new ArrayList<>();
        return and;
    }

    QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }

    QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }

    QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }

    QueryBuilder not(Matcher matcher) {
        this.matchers.add(new Not(matcher));
        return this;
    }

    QueryBuilder and(Matcher... matchers) {
        this.matchers.add(new And(matchers));
        return this;
    }

    QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }

}
