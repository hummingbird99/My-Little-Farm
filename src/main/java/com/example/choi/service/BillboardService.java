package com.example.choi.service;

import com.example.choi.domain.entity.Billboard;
import com.example.choi.domain.repository.BillboardRepository;
import com.example.choi.dto.BillboardDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillboardService {
    private BillboardRepository billboardRepository;

    public BillboardService(BillboardRepository billboardRepository) {
        this.billboardRepository = billboardRepository;
    }

    @Transactional
    public Long modifyPost(BillboardDto billboardDto) {
        Billboard billboard = billboardRepository.findById(billboardDto.getId()).get();
        billboardDto.setReadcnt(billboard.getReadcnt());
        return billboardRepository.save(billboardDto.toEntity()).getId();
    }

    @Transactional
    public Long savePost(BillboardDto billboardDto) {
        return billboardRepository.save(billboardDto.toEntity()).getId();
    }

    @Transactional
    public List<BillboardDto> getBillboardList(String bbstype) {
        Sort sort = Sort.by(Sort.Direction.DESC,"modifiedDate");
        List<Billboard> billboardList = billboardRepository.findAllByBbstype(bbstype, sort);
        List<BillboardDto> billboardDtoList = new ArrayList<>();

        for(Billboard billboard : billboardList) {
            BillboardDto billboardDto = BillboardDto.builder()
                    .id(billboard.getId())
                    .userid(billboard.getUserid())
                    .username(billboard.getUsername())
                    .title(billboard.getTitle())
                    .content(billboard.getContent())
                    .readcnt(billboard.getReadcnt())
                    .createdDate(billboard.getCreatedDate())
                    .build();
            billboardDtoList.add(billboardDto);
        }
        return billboardDtoList;
    }
    @Transactional
    public BillboardDto getPost(Long id) {
        Billboard billboard = billboardRepository.findById(id).get();

        BillboardDto billboardDto = BillboardDto.builder()
                .id(billboard.getId())
                .userid(billboard.getUserid())
                .username(billboard.getUsername())
                .title(billboard.getTitle())
                .content(billboard.getContent())
                .fileId(billboard.getFileId())
                .readcnt(billboard.getReadcnt())
                .bbstype(billboard.getBbstype())
                .createdDate(billboard.getCreatedDate())
                .modifiedDate(billboard.getModifiedDate())
                .build();
        return billboardDto;
    }
    @Transactional    public int updateView(Long id) {  return billboardRepository.updateView(id);    }

    @Transactional
    public void deletePost(Long id) {
        billboardRepository.deleteById(id);
    }
}
