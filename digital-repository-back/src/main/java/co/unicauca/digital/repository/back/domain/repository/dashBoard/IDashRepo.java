package co.unicauca.digital.repository.back.domain.repository.dashBoard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.DataSave;
import co.unicauca.digital.repository.back.domain.model.contract.Contract;


@Repository
public interface IDashRepo extends JpaRepository<Contract, Integer>{
    
    @Query("select new co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.DataSave(c.id, m.contractType.id, ct.name) "+
            "from "+
            "Contract c inner join Score s on c.id=s.contract.id "+
            "inner join ModalityContractType m on c.modalityContractType.id = m.id "+
            "inner join ContractType ct on m.contractType.id=ct.id "+
            "where ct.description=:desc and s.totalScore>=:min and s.totalScore<:max and year(s.createTime)=:year")
    List<DataSave> getDataByDes(@Param("desc") String desc, @Param("min") Float min, @Param("max") Float max, @Param("year") Integer year);

    @Query("select new co.unicauca.digital.repository.back.domain.dto.dashboard.Internal.DataSave(c.id, m.contractType.id, ct.name) "+
            "from "+
            "Contract c inner join Score s on c.id=s.contract.id "+
            "inner join ModalityContractType m on c.modalityContractType.id = m.id "+
            "inner join ContractType ct on m.contractType.id=ct.id "+
            "where s.createTime=s.updateTime")
    List<DataSave> getDataByDesNoScore(@Param("desc") String desc, @Param("year") Integer year);


    
    
}
