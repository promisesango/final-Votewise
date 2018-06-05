package tech.diggle.apps.accomodation.vote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.diggle.apps.accomodation.election.CouncilVoteModel
import tech.diggle.apps.accomodation.election.ElectionService
import tech.diggle.apps.accomodation.election.PresidentialVoteModel

@Service
class VoteServiceImpl(@Autowired val repository: VoteRepository,
                      @Autowired val electionService: ElectionService) : VoteService {
    override fun addVote(vote: Vote): Vote {
        return this.repository.save(vote)
    }

    override fun getAll(): List<Vote> {
        return this.repository.findAll()
    }


    override fun vote(id: Long, model: PresidentialVoteModel) {
        val vote = Vote()
        val election = electionService.get(id)
        vote.candidate = model.candidate
        vote.election = election
        vote.priority = 1
        repository.save(vote)
    }

    override fun vote(id: Long, model: CouncilVoteModel) {
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
}