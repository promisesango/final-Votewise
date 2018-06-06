package tech.diggle.apps.accomodation.election

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import tech.diggle.apps.accomodation.vote.VoteService
import javax.validation.Valid

@RequestMapping("election")
@Controller
class ElectionController(@Autowired val service: ElectionService,
                         @Autowired val voteService: VoteService) {
    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("elections", service.getAll())
        return "election/index"
    }

    @GetMapping("create")
    fun getCreate(): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("election", Election())
        mav.viewName = "election/create"
        return mav
    }

    @PostMapping("create")
    fun postCreate(@Valid election: Election, result: BindingResult): ModelAndView {
        val mav = ModelAndView()
        if (service.find(election) != null) result.reject("Similar election exists on same day")
        if (result.hasErrors()) {
            mav.viewName = ""
            return mav
        }

        val _election = service.add(election)
        return ModelAndView("redirect:/election/" + _election.id)
    }

    @GetMapping("{id}")
    fun election(@PathVariable id: Long, model: Model): String {
        val election = service.get(id)
        model.addAttribute("title", "Election details")
        model.addAttribute("election", election)
        model.addAttribute("candidates", election.candidates)
        return "election/election"
    }

    @GetMapping("{id}/results")
    fun electionResults(@PathVariable id: Long, model: Model): String {
        val election = service.get(id)
        model.addAttribute("title", "Election results")
        model.addAttribute("election", election)
        model.addAttribute("candidates", election.candidates)
        model.addAttribute("results", voteService.getResults(id))
        return "election/results"
    }

    @GetMapping("{id}/vote")
    fun getVote(@PathVariable id: Long, model: Model): String {
        val election = service.get(id)
        model.addAttribute("title", "Election details")
        model.addAttribute("election", election)
        model.addAttribute("candidates", election.candidates)
        model.addAttribute("pres", PresidentialVoteModel())
        model.addAttribute("coun", CouncilVoteModel())
        return "election/vote"
    }

    @PostMapping("{id}/vote/coun")
    fun postVotePresidential(@PathVariable("id") id: Long,
                             coun: CouncilVoteModel, result: BindingResult): ModelAndView {
        val mav = ModelAndView()
        if (result.hasErrors()) {
            mav.viewName = ""
            return mav
        }
        voteService.vote(id, coun)
        return ModelAndView("redirect:/election/$id/results")
    }

    @PostMapping("{id}/vote/pres")
    fun postVoteCouncil(@PathVariable("id") id: Long,
                        pres: PresidentialVoteModel, result: BindingResult): ModelAndView {
        val mav = ModelAndView()
        if (result.hasErrors()) {
            mav.viewName = ""
            return mav
        }
        voteService.vote(id, pres)
        return ModelAndView("redirect:/election/$id/results")
    }
}