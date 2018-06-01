package tech.diggle.apps.accomodation.election

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ElectionRepository : JpaRepository<Election, Long> {
    fun findElectionByDate(date: Date): Election?
    fun findByDateAndType(date: Date, type: ElectionType): Election
}