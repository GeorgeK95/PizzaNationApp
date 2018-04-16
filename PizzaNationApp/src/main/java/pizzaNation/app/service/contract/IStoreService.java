package pizzaNation.app.service.contract;

import pizzaNation.app.model.response.StoreResponseModel;

import java.util.Set;

/**
 * Created by George-Lenovo on 10/04/2018.
 */
public interface IStoreService {

    boolean persist(Double lat, Double lng);

    boolean delete(Double lat, Double lng);

    Set<StoreResponseModel> findAll();
}
