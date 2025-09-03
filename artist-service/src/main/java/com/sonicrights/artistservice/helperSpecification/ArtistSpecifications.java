package com.sonicrights.artistservice.helperSpecification;
import com.sonicrights.artistservice.entity.Artist;
import org.springframework.data.jpa.domain.Specification;

public class ArtistSpecifications {

    public static Specification<Artist> hasGenre(String genre) {
        return (root, query, cb) -> genre == null ? null : cb.equal(root.get("genre"), genre);
    }

    public static Specification<Artist> hasCountry(String country) {
        return (root, query, cb) -> country == null ? null : cb.equal(root.get("country"), country);
    }

    public static Specification<Artist> isActive(Boolean active) {
        return (root, query, cb) -> active == null ? null : cb.equal(root.get("active"), active);
    }
}
