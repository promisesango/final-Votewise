package tech.diggle.apps.accomodation.vote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/vote")
class VoteRestController(@Autowired val service: VoteService) {
    @PostMapping("{id}")
    fun postVotes(@PathVariable("id") electionId: Long){

    }
}