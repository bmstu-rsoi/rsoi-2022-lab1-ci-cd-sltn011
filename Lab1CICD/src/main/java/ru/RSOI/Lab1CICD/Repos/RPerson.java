package ru.RSOI.Lab1CICD.Repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.RSOI.Lab1CICD.Model.MPerson;

@Repository
public interface RPerson extends CrudRepository<MPerson, Integer> {
}
