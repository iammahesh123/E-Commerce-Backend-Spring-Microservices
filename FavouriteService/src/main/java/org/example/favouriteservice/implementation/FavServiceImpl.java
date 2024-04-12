package org.example.favouriteservice.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.favouriteservice.constant.AppConstant;
import org.example.favouriteservice.dtos.FavouriteDTO;
import org.example.favouriteservice.dtos.ProductDTO;
import org.example.favouriteservice.dtos.UserDTO;
import org.example.favouriteservice.exception.FavouriteNotFoundException;
import org.example.favouriteservice.helper.FavouriteMapping;
import org.example.favouriteservice.model.FavouriteId;
import org.example.favouriteservice.repository.FavouriteRepository;
import org.example.favouriteservice.service.FavouriteService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FavServiceImpl implements FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<FavouriteDTO> findAll() {
        log.info("*** FavouriteDto List, service; fetch all favourites *");
        return this.favouriteRepository.findAll()
                .stream()
                .map(FavouriteMapping::map)
                .map(f -> {
                    f.setUserDTO(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .USER_SERVICE_API_URL + "/" + f.getUserId(), UserDTO.class));
                    f.setProductDTO(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .PRODUCT_SERVICE_API_URL + "/" + f.getProductId(), ProductDTO.class));
                    return f;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public FavouriteDTO findById(final FavouriteId favouriteId) {
        log.info("*** FavouriteDto, service; fetch favourite by id *");
        return this.favouriteRepository.findById(favouriteId)
                .map(FavouriteMapping::map)
                .map(f -> {
                    f.setUserDTO(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .USER_SERVICE_API_URL + "/" + f.getUserId(), UserDTO.class));
                    f.setProductDTO(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .PRODUCT_SERVICE_API_URL + "/" + f.getProductId(), ProductDTO.class));
                    return f;
                })
                .orElseThrow(() -> new FavouriteNotFoundException(
                        String.format("Favourite with id: [%s] not found!", favouriteId)));
    }

    @Override
    public FavouriteDTO save(final FavouriteDTO favouriteDto) {
        return FavouriteMapping.map(this.favouriteRepository
                .save(FavouriteMapping.map(favouriteDto)));
    }

    @Override
    public FavouriteDTO update(final FavouriteDTO favouriteDto) {
        return FavouriteMapping.map(this.favouriteRepository
                .save(FavouriteMapping.map(favouriteDto)));
    }

    @Override
    public void deleteById(final FavouriteId favouriteId) {
        this.favouriteRepository.deleteById(favouriteId);
    }




}
