package tech.diggle.apps.accomodation.candidate

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Candidate {
    @Id
    @GeneratedValue
    val id: Long = 0

    @NotBlank
    var firstName: String = ""

    @NotBlank
    var lastName: String = ""


}