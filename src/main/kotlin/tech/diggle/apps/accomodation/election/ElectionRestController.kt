package tech.diggle.apps.accomodation.election

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.diggle.apps.accomodation.candidate.Candidate
import tech.diggle.apps.accomodation.vote.VoteService

@RestController
@RequestMapping("api/election")
class ElectionRestController(@Autowired val service: ElectionService,
                             @Autowired val voteService: VoteService) {
    @GetMapping("{id}/results")
    fun getResults(@PathVariable("id") id: Long): MutableList<Result> {
        val results: Map<Candidate, Int> = voteService.getResults(id)
        val response = mutableListOf<Result>()
        results.forEach {
            response.add(Result(it.key.firstName + " " + it.key.lastName, it.value))
        }
        response.sortByDescending { it.votes }
        return response
    }

    inner class Result(val name: String, val votes: Int)
}