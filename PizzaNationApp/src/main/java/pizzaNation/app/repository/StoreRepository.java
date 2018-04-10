package pizzaNation.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pizzaNation.app.model.entity.Store;

/**
 * Created by George-Lenovo on 10/04/2018.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    //    @Query("select count(s.id) from Store s where s.lat = :lat and s.lng = :lng") @Param("lat")
    boolean existsByLatAndLng(Double lat, Double lng);

    Store findByLatAndLng(Double lat, Double lng);
}
