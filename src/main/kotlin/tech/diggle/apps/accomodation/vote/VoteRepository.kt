package tech.diggle.apps.accomodation.vote

import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository: JpaRepository<Vote, Long>