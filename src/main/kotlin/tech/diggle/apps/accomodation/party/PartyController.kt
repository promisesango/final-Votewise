package tech.diggle.apps.accomodation.party

import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Controller
@RequestMapping("party")
class PartyController(val service: PartyService) {

    @GetMapping
    fun index(model: Model, @Param("q") query: Optional<String>): String {
        model.addAttribute("title", "Parties")
        if (!query.isPresent)
            model.addAttribute("parties", service.findAll())
        else model.addAttribute("parties", service.findParty(query.get()))
        return "party/index"
    }

    @GetMapping("create")
    fun getCreate(model: Model): String {
        model.addAttribute("title", "Create Party")
        model.addAttribute("party", Party())
        return "party/create"
    }

    @PostMapping("create")
    fun postCreate(@Valid _party: Party): String {
        val party = this.service.add(_party)
        return "redirect:/party/" + party.id
    }

    @GetMapping("{id}")
    fun getParty(@PathVariable("id") id: Long, model: Model) : String {
        val party = this.service.findOne(id)
        model.addAttribute("title", party.name )
        model.addAttribute("party" ,party )
        return "party/party"

    }

}