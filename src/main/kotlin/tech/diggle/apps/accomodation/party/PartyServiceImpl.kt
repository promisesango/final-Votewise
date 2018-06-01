package tech.diggle.apps.accomodation.party

import org.springframework.stereotype.Service
import javax.naming.OperationNotSupportedException

@Service
class PartyServiceImpl(val repository: PartyRepository) : PartyService {
    override fun findOne(id: Long) = this.repository.findOne(id)


    override fun findParty(param: String): List<Party> {
        var parties = this.repository.findByName(param)
        if (parties.isNotEmpty()) return parties

        parties = this.repository.findByMottoContainsIgnoreCase(param)
        return if (parties.isNotEmpty()) parties else this.repository.findByManifestoContainsIgnoreCase(param)
    }

    override fun findAll(): List<Party> {
        return this.repository.findAll()
    }

    override fun add(party: Party): Party {
        if (this.repository.findPartyByName(party.name) != null) throw IllegalArgumentException("Party Exists")
        return this.repository.save(party)
    }

    override fun update(party: Party): Party {
        if (this.repository.findOne(party.id) == null) throw IllegalArgumentException("Party does not exist")
        return this.repository.save(party)
    }

    override fun delete(party: Party): Party {
        throw OperationNotSupportedException("Deleting party")
    }
}