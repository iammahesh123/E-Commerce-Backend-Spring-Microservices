package org.example.favouriteservice.repository;

import org.example.favouriteservice.model.Favourite;
import org.example.favouriteservice.model.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
}
