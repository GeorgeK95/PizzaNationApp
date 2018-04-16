package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaNation.app.model.entity.Store;
import pizzaNation.app.model.response.StoreResponseModel;
import pizzaNation.app.repository.StoreRepository;
import pizzaNation.app.service.contract.IStoreService;
import pizzaNation.app.util.DTOConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by George-Lenovo on 10/04/2018.
 */
@Service
@Transactional
public class StoreService implements IStoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public boolean persist(Double lat, Double lng) {
        if (!this.storeRepository.existsByLatAndLng(lat, lng))
            this.storeRepository.saveAndFlush(new Store(lat, lng));
        return true;
    }

    @Override
    public boolean delete(Double lat, Double lng) {
        Store store = this.storeRepository.findByLatAndLng(lat, lng);
        this.storeRepository.delete(store);
        return true;
    }

    @Override
    public Set<StoreResponseModel> findAll() {
        List<Store> all = this.storeRepository.findAll();
        Set<StoreResponseModel> models = new HashSet<>();
        all.forEach(s -> models.add(DTOConverter.convert(s, StoreResponseModel.class)));
        return models;
    }
}
