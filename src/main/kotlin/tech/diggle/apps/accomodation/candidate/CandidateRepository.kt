package tech.diggle.apps.accomodation.candidate

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface CandidateRepository : PagingAndSortingRepository<Candidate, Long> {
    fun findByElectionId(id: Long, pageRequest: Pageable): Page<Candidate>
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Candidate?
}