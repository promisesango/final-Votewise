package tech.diggle.apps.accomodation.candidate

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tech.diggle.apps.accomodation.election.ElectionService
import tech.diggle.apps.accomodation.party.PartyService
import javax.validation.Valid

@Controller
@RequestMapping("candidate")
class CandidateController(val service: CandidateService, val partyService: PartyService,
                          val electionService: ElectionService) {
    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("title", "All Candidates")
        model.addAttribute("candidates", service.getAll())
        return "candidate/index"
    }

    @GetMapping("{id}")
    fun indexOfElection(model: Model, @PathVariable(name = "id") id: Long): String {
        model.addAttribute("title", "All Candidates")
        model.addAttribute("candidates", service.getAll(id))
        model.addAttribute("election", electionService.get(id))
        return "candidate/index"
    }


    @GetMapping("{id}/create")
    fun createCandidate(model: Model, @PathVariable(name = "id") id: Long): String {
        model.addAttribute("title", "Create Candidate")
        model.addAttribute("election", electionService.get(id))
        model.addAttribute("party", partyService.findAll())
        model.addAttribute("candidate", Candidate())
        return "candidate/create"
    }


    @PostMapping("{id}/create")
    fun postCreateCandidate(model: Model,
                            @PathVariable(name = "id") id: Long,
                            @Valid candidate: Candidate, result: BindingResult): String {
        if (service.find(candidate) != null) result.reject("candidate with same name is already registered")
        if (result.hasErrors()) {
            return ""
        }
        candidate.election = electionService.get(id)
        val candidate_ = service.add(candidate)
        return "redirect:/candidate/$id"
    }


}