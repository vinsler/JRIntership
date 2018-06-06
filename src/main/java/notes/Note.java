package notes;

import java.util.Date;

public class Note {

    private int id;
    private String name;
    private String description;
    private int status;
    private Date createDate;

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setNote(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
