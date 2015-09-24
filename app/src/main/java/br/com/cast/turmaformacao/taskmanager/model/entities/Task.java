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

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public Long getWebId() {
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

        if (label != null ? !label.equals(task.label) : task.label != null) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (webId != null ? !webId.equals(task.webId) : task.webId != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        return !(description != null ? !description.equals(task.description) : task.description != null);

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (webId != null ? webId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }



    @Override
    public String toString() {
        return "Task{" +
                "Web_id=" + webId +
                ", id=" + id +
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
        dest.writeLong(id == null ? -1 : id);
        dest.writeLong(webId == null ? -1 : webId);
        dest.writeString(name == null ? "" : name);
        dest.writeString(description == null ? "" : description);
        dest.writeParcelable(label == null ? new Label() : label, flags);
    }

    public void readFromParcel(Parcel imp) {

        id = imp.readLong();
        id = id == -1 ? null : id;

        webId = imp.readLong();
        webId = webId == -1 ? null : webId;

        name = imp.readString();
        description = imp.readString();

        label = imp.readParcelable(Label.class.getClassLoader());

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
