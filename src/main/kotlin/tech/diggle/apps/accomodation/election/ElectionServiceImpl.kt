package tech.diggle.apps.accomodation.election

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElectionServiceImpl(@Autowired val repository: ElectionRepository) : ElectionService {
    override fun getAll(): List<Election> = repository.findAll()

    override fun get(id: Long): Election = repository.getOne(id)

    override fun add(election: Election): Election {
        return this.repository.save(election)
    }

    override fun update(election: Election): Election {
        return this.repository.save(election)
    }

    override fun find(election: Election): Election? {
        return this.repository.findByDateAndType(election.date!!, election.type!!)
    }

}