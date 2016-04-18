package org.itsurvival.books.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Author implements Serializable {

  public final static String PERSONAL_DATA_PROPERTY = "personalData";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  @Column(nullable = true, length = 30)
  protected String nickName;

  @Embedded
  @AttributeOverrides({
  @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false) ),
  @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false) ) })
  protected PersonalData personalData;

  @Version
  protected long version;

  // for hibernate
  public Author() {

  }

  public Author(Long id, PersonalData personalData, String nickName, long version) {
    this.id = id;
    this.version = version;
    this.personalData = personalData;
    this.nickName = nickName;
  }

  public Long getId() {

    return this.id;
  }

  public PersonalData getPersonalData() {

    return this.personalData;
  }

  public String getNickName() {

    return this.nickName;
  }

  public long getVersion() {

    return this.version;
  }

  public void setVersion(long version) {

    this.version = version;
  }
}
