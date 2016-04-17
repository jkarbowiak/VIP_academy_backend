package org.itsurvival.books.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    public static final String PERSONAL_DATA_PROPERTY = "personalData";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = true, length = 30)
    protected String nickName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false))})
    protected PersonalData personalData;

    @Version
    protected long version;

    // for hibernate
    protected Author() {

    }

    public Author(Long id, PersonalData personalData, String nickName, long version) {
        this.id = id;
        this.version = version;
        this.personalData = personalData;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public String getNickName() {
        return nickName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
