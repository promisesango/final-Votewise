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
import javax.validation.Valid

@RequestMapping("election")
@Controller
class ElectionController(@Autowired val service: ElectionService) {
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
        return "election/election"
    }


}