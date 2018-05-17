package tech.diggle.apps.accomodation.candidates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @RequestMapping(value = "/candidate",method = RequestMethod.GET)
    public ModelAndView listAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("candidate", candidateService.listAll());
        modelAndView.setViewName("admin/candidates/listCandidates");
        return modelAndView;
    }


    @RequestMapping(value = "/createCandidate", method = RequestMethod.GET)
    public ModelAndView createCandidate() {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        modelAndView.addObject("candidate", candidate);
        modelAndView.setViewName("admin/candidates/createCandidate");
        return modelAndView;
    }

    @RequestMapping(value = "/createCandidate", method = RequestMethod.POST)
    public ModelAndView createCandidate(@Valid Candidate candidate, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidateExists = candidateService.findCandidateByCandidateName(candidate.getCandidateName());
        if (candidateExists != null) {
            bindingResult
                    .rejectValue("candidateName", "candidate.error",
                            "There is already a candidate registered with the candidate name  provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/candidates/createCandidate");
        } else {
            candidateService.createCandidate(candidate);
            modelAndView.addObject("successMessage", "Candidate has been registered successfully");
            modelAndView.addObject("candidate", new Candidate());
            modelAndView.setViewName("admin/candidates/createCandidate");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/updateCandidate", method = RequestMethod.GET)
    public ModelAndView updateCandidate(Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/candidates/updateCandidate");
        return modelAndView;
    }

    @RequestMapping(value = "/findCandidateByName", method = RequestMethod.GET)
    public ModelAndView findCandidateByName(String candidateName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/candidates/findCandidateByName");
        return modelAndView;
    }

    @RequestMapping(value = "/findCandidateByParty", method = RequestMethod.GET)
    public ModelAndView findCandidateByParty(String party) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("findCandidateByParty");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteCandidate", method = RequestMethod.DELETE)
    public ModelAndView deleteCandidate(Candidate candidate) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteCandidate");
        return modelAndView;
    }


}
