package co.unicauca.digital.repository.back.domain.repository.document;

import co.unicauca.digital.repository.back.domain.model.document.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentRepository extends JpaRepository<Document, Integer> {
    Page<Document> findAll(Pageable pageable);
}