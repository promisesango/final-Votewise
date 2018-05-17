package tech.diggle.apps.accomodation.candidates;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@TableGenerator(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "candidateId")
    private long id;

    @Column(name = "candidateName")
    @NotNull
    @Length(min = 5, message = "*Your candidate name must have at least 5 characters")
    private String candidateName;

    @Column(name = "motivation")
    @NotNull
    @NotEmpty(message = "*Please provide your motivation")
    @Length(min = 5, max = 20, message = "*Your candidate name must have at least 5 characters")
    private String motivation;

    @Column(name = "party")
    @NotNull
    @NotEmpty(message = "*Please provide your party")
    private String party;


    public Candidate(long id, String candidateName, String motivation, String party) {
        this.id = id;
        this.candidateName = candidateName;
        this.motivation = motivation;
        this.party = party;


    }

    public Candidate() {
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }


}
