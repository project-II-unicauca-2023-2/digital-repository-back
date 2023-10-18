package co.unicauca.digital.repository.back.domain.repository.score;

import co.unicauca.digital.repository.back.domain.model.score.Criteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICriteriaRepository extends JpaRepository<Criteria,Integer>{
    /**
     * Soon queries necessary to connect to the database
     */
}
