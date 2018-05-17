package tech.diggle.apps.accomodation.candidates;

import java.util.List;

public interface CandidateService {
    Candidate findCandidateByCandidateName(String candidateName);

    Candidate findCandidateByParty(String party);

    Candidate updateCandidate(Candidate candidate);

    Candidate createCandidate(Candidate candidate);

    boolean deleteCandidate(Candidate candidate);

    List<Candidate> listAll();
}
