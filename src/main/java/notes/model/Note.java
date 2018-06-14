package notes.model;

import java.util.Date;

public class Note {
    private int id;
    private String name;
    private String description;
    private Date createDate;
    private int status;
    private int users_id;

    public void setNote(int id) {
        this.id = id;
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
    public int getStatus() {
        return status;
    }
    public int getUsers_id() {
        return users_id;
    }

    public void setId(int id) {
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
    public void setStatus(int status) {
        this.status = status;
    }
    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}
