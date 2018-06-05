package tech.diggle.apps.accomodation.candidate

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.apps.accomodation.election.Election
import tech.diggle.apps.accomodation.party.Party
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Candidate {
    @Id
    @GeneratedValue
    val id: Long = 0

    @NotBlank
    var firstName: String = ""

    @NotBlank
    var lastName: String = ""

    @ManyToOne
    @NotNull
    var election: Election? = null

    @ManyToOne
    @NotNull
    var party: Party? = null


}