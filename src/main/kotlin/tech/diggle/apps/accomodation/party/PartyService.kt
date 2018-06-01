package tech.diggle.apps.accomodation.party

interface PartyService {
    fun findParty(param: String): List<Party>
    fun findAll(): List<Party>
    fun add(party: Party): Party
    fun update(party: Party): Party
    fun delete(party: Party): Party
    fun findOne(id: Long):Party
}