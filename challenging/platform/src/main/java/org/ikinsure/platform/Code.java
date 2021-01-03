package org.ikinsure.platform;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Code {

    @Id
    private String id;
    @Column(length = 2048)
    private String code;
    private String date;
    private LocalDateTime createDate;
    private LocalDateTime destroyDate;
    private long time;
    private long views;

    protected Code() {}

    public Code(String id, String code, String date, LocalDateTime createDate, LocalDateTime destroyDate, long time, long views) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.createDate = createDate;
        this.destroyDate = destroyDate;
        this.time = time;
        this.views = views;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonIgnore
    public LocalDateTime getDestroyDate() {
        return destroyDate;
    }

    public void setDestroyDate(LocalDateTime destroyDate) {
        this.destroyDate = destroyDate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
