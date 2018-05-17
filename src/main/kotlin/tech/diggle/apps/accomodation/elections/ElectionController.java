package tech.diggle.apps.accomodation.elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/election")
public class ElectionController {

    @Autowired
    ElectionService electionService;

    @RequestMapping(value = "listElections", method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("election", electionService.listAll());
        modelAndView.setViewName("admin/elections/listElections");
        return modelAndView;
    }

    @RequestMapping(value = "createElection", method = RequestMethod.GET)
    public ModelAndView createElection() {
        ModelAndView modelAndView = new ModelAndView();
        Election election = new Election();
        modelAndView.addObject("election", election);
        modelAndView.setViewName("admin/elections/createElection");
        return modelAndView;
    }

    @RequestMapping(value = "createElection", method = RequestMethod.POST)
    public ModelAndView createElection(@Valid Election election, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Date electionExists = electionService.findElectionByElectionDate(election.getElectionDate());

        if (electionExists != null) {

            bindingResult.rejectValue("electionDate", "elections.error", "There is already an elections created with  same date  provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/elections/createElection");
        } else {
            electionService.createElection(election);
            modelAndView.addObject("successMessage", "elections has been created successfully");
            modelAndView.addObject("election", new Election());
            modelAndView.setViewName("admin/elections/createElection");
        }
        return modelAndView;


    }


}
