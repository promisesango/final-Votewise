package tech.diggle.apps.accomodation.candidate

interface CandidateService {
    fun getAll(): List<Candidate>
    fun getAll(id: Long): List<Candidate>
    fun add(candidate: Candidate): Candidate
    fun delete(id: Long)
    fun find(candidate: Candidate):Candidate?
}