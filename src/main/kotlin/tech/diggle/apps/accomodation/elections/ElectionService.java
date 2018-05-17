package tech.diggle.apps.accomodation.elections;



import java.util.Date;
import java.util.List;


public interface ElectionService {
    List<Election> listAll();

    Election createElection(Election election);

    boolean deleteElection(Election election);

    Date findElectionByElectionDate(Date election);

    Election findElectionByElectionType(String election);


}
