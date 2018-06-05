package tech.diggle.apps.accomodation.vote

import tech.diggle.apps.accomodation.candidate.Candidate
import tech.diggle.apps.accomodation.election.Election
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
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