package tech.diggle.apps.accomodation.candidates;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByCandidateName(String candidateName);

    Candidate findByParty(String party);
}
