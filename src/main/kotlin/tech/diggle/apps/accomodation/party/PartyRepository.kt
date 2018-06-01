package tech.diggle.apps.accomodation.party

import org.springframework.data.jpa.repository.JpaRepository

interface PartyRepository : JpaRepository<Party, Long> {
    fun findPartyByName(name: String): Party?
    fun findByName(name: String): List<Party>
    fun findByMottoContainsIgnoreCase(motto: String): List<Party>
    fun findByManifestoContainsIgnoreCase(manifesto: String): List<Party>
}