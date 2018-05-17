package tech.diggle.apps.accomodation.candidates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    @Qualifier("candidateRepository")
    private CandidateRepository candidateRepository;

    @Override
    @Transactional
    public Candidate findCandidateByCandidateName(String CandidateName) {
        return candidateRepository.findByCandidateName(CandidateName);
    }

    @Override
    @Transactional
    public Candidate findCandidateByParty(String party) {

        return candidateRepository.findByParty(party);
    }

    @Override
    @Transactional
    public Candidate updateCandidate(Candidate candidate) {
        Candidate existingCandidate = candidateRepository.findByCandidateName(candidate.getCandidateName());
        if (existingCandidate == null) {
            throw new RuntimeException("Record doesnt exists!");
        }
        existingCandidate.setCandidateName("name");
        existingCandidate.setMotivation("motivation");
        existingCandidate.setParty("party");
        return candidateRepository.save(existingCandidate);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        candidate.setCandidateName(candidate.getCandidateName());
        candidate.setMotivation(candidate.getMotivation());
        candidate.setParty(candidate.getParty());
        candidateRepository.save(candidate);
        return candidate;
    }

    @Override
    @Transactional
    public boolean deleteCandidate(Candidate candidate) {
        Candidate existingCandidate = candidateRepository.findByCandidateName(candidate.getCandidateName());
        if (existingCandidate == null) {
            return false;
        }
        candidateRepository.delete(existingCandidate);
        return false;
    }

    @Override
    public List<Candidate> listAll() {
        return candidateRepository.findAll();
    }
}
