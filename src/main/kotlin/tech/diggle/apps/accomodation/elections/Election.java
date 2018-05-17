package tech.diggle.apps.accomodation.elections;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@TableGenerator(name = "elections")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "electionType")
    private String electionType;

    @NotNull
    @Column(name = "electionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date electionDate;

    public Election(@NotNull String electionType, @NotNull Date electionDate) {
        this.electionType = electionType;
        this.electionDate = electionDate;
    }

    public Election() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public Date getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(Date electionDate) {
        this.electionDate = electionDate;
    }
}
