package tech.diggle.apps.accomodation.election

import org.springframework.format.annotation.DateTimeFormat
import tech.diggle.apps.accomodation.candidate.Candidate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @NotNull
    @Column(name = "type")
    var type: ElectionType? = null

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var date: Date? = null

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ ")
    var startDate: Date? = null

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ ")
    var endDate: Date? = null

    var status: ElectionStatus? = null

    @OneToMany
    var candidates: List<Candidate> = emptyList()
}

