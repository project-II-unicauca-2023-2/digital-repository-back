package co.unicauca.digital.repository.back.domain.repository.criteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoConsultResponse;
import co.unicauca.digital.repository.back.domain.model.criteria.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * Repository that manages the persistence of the Score entity in the database.
 */
@Repository
public interface ICriteriaRepository extends JpaRepository<Criteria, Integer> {
    /**
     * Query find Criteria by Id
     */
    Optional<Criteria> findById(Integer id);

    /**
     * Query fin criteria by type
     * @param criteriaType
     * @return list of criteria
     */
    //@Query("SELECT c.id, c.name, c.description FROM Criteria c where c.criteriaType=:paramType")
    //List<CriteriaDtoConsultResponse> getCriteriaByType(@Param("paramType") String criteriaType);

    @Query("SELECT new co.unicauca.digital.repository.back.domain.dto.criteria.response.CriteriaDtoConsultResponse(c.id, c.name, c.description) FROM Criteria c where c.criteriaType=:paramType")
    List<CriteriaDtoConsultResponse> getCriteriaByType(@Param("paramType") String criteriaType);

    

}