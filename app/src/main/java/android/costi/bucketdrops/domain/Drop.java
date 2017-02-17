package android.costi.bucketdrops.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Costi on 16.02.2017.
 */

public class Drop extends RealmObject{
    private String what;
    @PrimaryKey
    private long added;
    private long when;
    private boolean done;

    public Drop() {
    }

    public Drop(String what, long added, long when, boolean done) {
        this.what = what;
        this.added = added;
        this.when = when;
        this.done = done;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
