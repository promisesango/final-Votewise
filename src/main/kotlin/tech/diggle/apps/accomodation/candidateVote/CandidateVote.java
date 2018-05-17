package tech.diggle.apps.accomodation.candidateVote;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@TableGenerator(name = "CandidateVote")
public class CandidateVote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private long id;


    @NotNull
    @Column(name = "voteCount")
    private long voteCount;

    public CandidateVote(@NotNull long voteCount) {
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
