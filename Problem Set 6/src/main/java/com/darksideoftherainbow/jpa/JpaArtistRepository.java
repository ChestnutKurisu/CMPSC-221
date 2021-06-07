package com.darksideoftherainbow.jpa;

import com.darksideoftherainbow.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface JpaArtistRepository extends CrudRepository<Artist, Integer> {
}
