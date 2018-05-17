package tech.diggle.apps.accomodation.elections;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ElectionRepository extends JpaRepository<Election, Long> {


    Election findElectionByElectionType(String election);

    Date findElectionByElectionDate(Date election);
}
