package pl.coderslab.charity.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query(value = "SELECT * FROM institution LIMIT ?1", nativeQuery = true)
    public List<Institution> queryInstitution(int limit);
}
