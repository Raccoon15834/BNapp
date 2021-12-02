package das.anusha.bnapp;

import java.sql.Timestamp;

public class PostData {
    private String content, title, name;
    Timestamp date;

    public PostData(String content, String title, String name) {
        this.content = content;
        this.title = title;
        this.name = name;
    }
    public String toJson(){
        return "";
    }
}
