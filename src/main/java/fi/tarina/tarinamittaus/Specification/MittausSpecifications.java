package fi.tarina.tarinamittaus.Specification;

import fi.tarina.tarinamittaus.Model.Mittaus;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;

public class MittausSpecifications {

    /** if keyword == null then specification is ignored */
    public static Specification<Mittaus> mittausKeywordLike(String keyword) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                keyword == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("mittaus_asianhallinta_id"), keyword),
                        criteriaBuilder.like(root.get("pdf_raportin_linkki"), keyword),
                        criteriaBuilder.like(root.get("perustamistapa"), keyword),
                        criteriaBuilder.like(root.get("julkisivumateriaali"), keyword),
                        criteriaBuilder.like(root.get("runkomateriaali"), keyword),
                        criteriaBuilder.like(root.get("katuosoite"), keyword),
                        criteriaBuilder.like(root.get("postinumero"), keyword),
                        criteriaBuilder.like(root.get("created_by_lx"), keyword)
                                  )
                );
    }
    public static Specification<Mittaus> mittausSquareArea(Double area) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                area == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("rakennuksen_pinta_ala"), area));
    }
}
