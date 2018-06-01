package tech.diggle.apps.accomodation.party

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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
}