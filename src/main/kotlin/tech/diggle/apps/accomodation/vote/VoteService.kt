package tech.diggle.apps.accomodation.vote

import tech.diggle.apps.accomodation.election.CouncilVoteModel
import tech.diggle.apps.accomodation.election.PresidentialVoteModel

interface VoteService{
    fun addVote(vote: Vote): Vote
    fun getAll(): List<Vote>
    fun vote(id: Long, model: PresidentialVoteModel)
    fun vote(id: Long, model: CouncilVoteModel)
}