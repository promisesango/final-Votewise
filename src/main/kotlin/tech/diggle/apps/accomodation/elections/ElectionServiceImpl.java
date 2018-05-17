package tech.diggle.apps.accomodation.elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ElectionServiceImpl  implements ElectionService{

    @Autowired
    @Qualifier("electionRepository")
    ElectionRepository electionRepository;



    @Override
    public List<Election> listAll() {
        return  electionRepository.findAll();
    }

    @Override
    public Election createElection(Election election) {

        election.setElectionDate(election.getElectionDate());
        election.setElectionType(election.getElectionType());
        electionRepository.save(election);
        return election;
    }

    @Override
    public boolean deleteElection(Election election) {
        return false;
    }

    @Override
    public Date findElectionByElectionDate(Date election) {
        return electionRepository.findElectionByElectionDate(election);
    }

    @Override
    public Election findElectionByElectionType(String election) {
        return electionRepository.findElectionByElectionType(election);
    }
}
