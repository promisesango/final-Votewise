package tech.diggle.apps.accomodation.candidate

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CandidateServiceImpl(val repository: CandidateRepository) : CandidateService {
    override fun find(candidate: Candidate): Candidate? {
    return this.repository.findByFirstNameAndLastName(candidate.firstName!!, candidate.lastName!!)
    }

    override fun getAll(): List<Candidate> {
        return this.repository.findAll(PageRequest(0, 20)).content
    }

    override fun getAll(id: Long): List<Candidate> {
        return this.repository.findByElectionId(id, PageRequest(0, 20)).content
    }

    override fun add(candidate: Candidate): Candidate {
        return this.repository.save(candidate)
    }

    override fun delete(id: Long) {
        return this.repository.delete(id)
    }
}