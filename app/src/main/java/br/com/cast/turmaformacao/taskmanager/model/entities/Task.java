package br.com.cast.turmaformacao.taskmanager.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task implements Parcelable {
    private Label label;
    private Long id;

    @JsonProperty("_id")
    private Long webId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;



    public Task() {
        super();
    }

    public Task(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public void setwebId(Long webId) {
        this.webId = webId;
    }

    public Long getwebId() {
        return webId;
    }

    public Long getId() {
        return id;
    }


    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if(!webId.equals(task.webId)) return false;
        if (!id.equals(task.id)) return false;
        if (!name.equals(task.name)) return false;
        return description.equals(task.description);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + webId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "webId=" + webId +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(webId == null ? -1 : webId);
        dest.writeLong(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
    }

    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        webId = webId == -1 ? null : webId;
        id = id == -1 ? null : id;
        name = imp.readString();
        description = imp.readString();

    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}
