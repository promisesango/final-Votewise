package tech.diggle.apps.accomodation.vote

import tech.diggle.apps.accomodation.candidate.Candidate
import tech.diggle.apps.accomodation.election.Election
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Vote {
    @Id
    @GeneratedValue
    val id: Long = 0

    var priority: Int = 0

    @ManyToOne
    @NotNull
    var candidate: Candidate? = null

    @ManyToOne
    @NotNull
    var election: Election? = null
}

@Entity
@IdClass(CandidateVoteKey::class)
class CandidateVote(){
    @Id
    var userId: Long = 0
    @Id
    var electionId: Long = 0
    constructor(
                userId: Long,
                electionId: Long) : this(){
        this.userId = userId
        this.electionId = electionId
    }
}

class CandidateVoteKey : Serializable {
    var userId: Long = 0
    var electionId: Long = 0
}