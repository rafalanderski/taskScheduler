package pl.coderslab.taskscheduler.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=1, max=200)
    @NotEmpty
    private String title;

    @Size(min=1, max=200)
    @NotEmpty
    private String content;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer priority;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer severity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskStatusOptions status = TaskStatusOptions.NEW;

    private Date created = new java.util.Date();

    private Date activated = new java.util.Date();

    private Date resolved = new java.util.Date();

    private Date closed = new java.util.Date();

    private Date modified = new java.util.Date();

    public long getId() {
        return id;
    }

    public void setId(long id){this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskStatusOptions getStatus() {
        return status;
    }

    public void setStatus(TaskStatusOptions status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getActivated() {
        return activated;
    }

    public void setActivated(Date activated) {
        this.activated = activated;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", priority=" + priority +
                ", severity=" + severity +
                ", user=" + user +
                ", status=" + status +
                ", created=" + created +
                ", activated=" + activated +
                ", resolved=" + resolved +
                ", closed=" + closed +
                ", modified=" + modified +
                '}';
    }
}

