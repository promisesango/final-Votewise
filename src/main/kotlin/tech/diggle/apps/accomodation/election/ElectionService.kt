package tech.diggle.apps.accomodation.election

interface ElectionService {
    fun getAll(): List<Election>
    fun get(id: Long): Election
    fun getResults(id: Long): Any
    fun add(election: Election): Election
    fun update(election: Election): Election
    fun find(election: Election): Election?
}