package tech.diggle.apps.accomodation.party

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.apps.accomodation.candidate.Candidate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Party {
    @Id
    @GeneratedValue
    val id: Long = 0

    @NotBlank
    var name: String = ""

    @NotBlank
    var motto: String = ""

    @NotBlank
    var manifesto: String = ""

    @OneToMany
    var candidates: List<Candidate> = emptyList()
}