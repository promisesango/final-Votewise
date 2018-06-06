package tech.diggle.apps.accomodation.vote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import tech.diggle.apps.accomodation.election.CouncilVoteModel
import tech.diggle.apps.accomodation.election.ElectionService
import tech.diggle.apps.accomodation.election.PresidentialVoteModel
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import tech.diggle.apps.accomodation.candidate.Candidate
import tech.diggle.apps.accomodation.user.UserRepository


@Service
class VoteServiceImpl(@Autowired val repository: VoteRepository,
                      @Autowired val candidateVoteRepository: CandidateVoteRepository,
                      @Autowired val electionService: ElectionService,
                      @Qualifier("userRepository")
                      @Autowired val userRepository: UserRepository) : VoteService {
    override fun addVote(vote: Vote): Vote {
        return this.repository.save(vote)
    }

    override fun getAll(): List<Vote> {
        return this.repository.findAll()
    }

    override fun vote(id: Long, model: PresidentialVoteModel) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication is AnonymousAuthenticationToken) return
        val userId = userRepository.findByEmail(authentication.name).id.toLong()

        val cVoteK = CandidateVoteKey()
        cVoteK.electionId = id
        cVoteK.userId = userId
        if (candidateVoteRepository.exists(cVoteK)) throw IllegalStateException("Already Voted")

        candidateVoteRepository.save(CandidateVote(userId, id))

        val vote = Vote()
        val election = electionService.get(id)
        vote.candidate = model.candidate
        vote.election = election
        vote.priority = 1
        repository.save(vote)

    }

    override fun vote(id: Long, model: CouncilVoteModel) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication is AnonymousAuthenticationToken) return
        val userId = userRepository.findByEmail(authentication.name).id.toLong()

        val cVoteK = CandidateVoteKey()
        cVoteK.electionId = id
        cVoteK.userId = userId
        if (candidateVoteRepository.exists(cVoteK)) throw IllegalStateException("Already Voted")
        candidateVoteRepository.save(CandidateVote(userId, id))

        if (model.candidate1 != null) {
            val vote = Vote()
            val election = electionService.get(id)
            vote.candidate = model.candidate1
            vote.election = election
            vote.priority = 5
            repository.save(vote)
        }
        if (model.candidate2 != null) {
            val vote = Vote()
            val election = electionService.get(id)
            vote.candidate = model.candidate2
            vote.election = election
            vote.priority = 4
            repository.save(vote)
        }
        if (model.candidate3 != null) {
            val vote = Vote()
            val election = electionService.get(id)
            vote.candidate = model.candidate3
            vote.election = election
            vote.priority = 3
            repository.save(vote)
        }
        if (model.candidate4 != null) {
            val vote = Vote()
            val election = electionService.get(id)
            vote.candidate = model.candidate4
            vote.election = election
            vote.priority = 2
            repository.save(vote)
        }
        if (model.candidate5 != null) {
            val vote = Vote()
            val election = electionService.get(id)
            vote.candidate = model.candidate5
            vote.election = election
            vote.priority = 1
            repository.save(vote)
        }
    }

    override fun getResults(electionId: Long): Any {
        val election = electionService.get(electionId) ?: throw IllegalArgumentException("")
        val votes = repository.getByElectionId(electionId)
        val candidates = election.candidates
        val results: MutableMap<Candidate, Int> = mutableMapOf()
        for (candidate in candidates)
            results[candidate] = votes.filter { it.candidate == candidate }.count()
        return results
    }
}