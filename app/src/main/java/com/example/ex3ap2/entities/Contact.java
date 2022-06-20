package com.example.ex3ap2.entities;

        import androidx.annotation.NonNull;
        import androidx.room.Entity;
        import androidx.room.PrimaryKey;

        import com.example.ex3ap2.R;

@Entity
public class Contact {
    @PrimaryKey
    @NonNull
    private String id;

    private String nickname;

    private String server;

    private String last;

    private String lastdate;

    private int pic;

    public Contact(String id, String nickname, String server, String last, String lastdate) {
        this.id = id;
        this.nickname = nickname;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.pic = R.drawable.ic_defaultimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
